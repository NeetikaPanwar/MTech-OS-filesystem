package org.iiitb.os.os_proj.shell;

import org.iiitb.os.os_proj.User;
import org.iiitb.os.os_proj.commands.ICommand;
import org.iiitb.os.os_proj.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shell extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROWS = 26;
    public static final int COLUMNS = 43;
    public static final int LINE_LENGTH = 407;
    public static String userString = "";
    private static String SHELLTEXT = "\t              Welcome to KanchuFS Shell \n\nEnter Username:";
    private static String SHELLTEXTINCORRECTLOGIN = "\t              Welcome to KanchuFS Shell \n\nIncorrect Login...Try again \nEnter Username:";
    private boolean isLoginUserName = true;
    private boolean isLoginPassword = false;
    private boolean firstcommand = false;
    private String sudopassword="";

    private boolean isRoot=false;
    private boolean sudoProcedureOngoing=false;

    private String receivedString = null;

    private String username;
    private String password;

    private JTextArea shellArea;
    private JTextArea command;

    private Border border;

    private String sudocommand;


    private Controller controller;

    public Shell() {

        controller = new Controller("", new User());

        JPanel shellPanel = new JPanel();
        shellPanel.setBackground(new Color(0, 0, 0));

        border = BorderFactory.createLineBorder(Color.BLACK);

        shellArea = new JTextArea(ROWS, COLUMNS);
        shellArea.setText(SHELLTEXT);
        shellArea.setVisible(true);
        shellArea.setLineWrap(true);
        shellArea.setWrapStyleWord(true);
        shellArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 10, 0, 10)));
        shellArea.setEditable(false);
        shellArea.setBackground(new Color(0, 0, 0));
        shellArea.setForeground(new Color(255, 255, 255));

        command = new JTextArea(COLUMNS - ROWS, COLUMNS);
        command.setForeground(new Color(255, 255, 255));
        command.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(0, 10, 5, 10)));
        command.setBackground(new Color(0, 0, 0));
        command.setCaretColor(new Color(255, 255, 255));
        command.requestFocus();
        command.setLineWrap(true);
        command.setWrapStyleWord(true);

        shellPanel.setLayout(new BorderLayout());
        shellPanel.add(shellArea, BorderLayout.NORTH);
        shellPanel.add(command, BorderLayout.AFTER_LINE_ENDS);
        shellPanel.setSize(WIDTH, HEIGHT);


        Font font = new Font("Arial", Font.PLAIN, 12);
        Canvas c = new Canvas();
        FontMetrics fm = c.getFontMetrics(font);
        int lines = fm.stringWidth(shellArea.getText()) / LINE_LENGTH;

        shellArea.setRows(lines);
        //revalidate();

        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                command.requestFocusInWindow();
            }
        });

        command.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (isLoginPassword) {
                    command.setForeground(new Color(0, 0, 0));
                    command.setCaretPosition(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (command.getText().length() > userString.length()) {
                        callRobotBackspace();
                    } else {
                        e.consume();
                    }


                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //System.out.println(command.getText());
                    if(command.getText().length()>userString.length()+4 && command.getText().substring(userString.length(),userString.length()+4).equals("sudo")){
                        if(!isRoot){
                          sudoProcedureOngoing=true;
                          sudocommand=command.getText().substring(userString.length()+4);

                          updateShell("\nEnter root password:");
                          command.setText("");
                          command.setCaretColor(new Color(0,0,0));
                          command.setForeground(new Color(0,0,0));
                        } else{
                            receivedString = controller.call(sudocommand);
                            updateShell(receivedString);
                        }
                    }
                    else if(sudoProcedureOngoing){
                        sudopassword = command.getText();
                        if(sudopassword.substring(1, command.getText().length()).equals(Controller.CURRENT_USER.getPasswordHash())){
                            command.setCaretColor(new Color(255,255,255));
                            command.setForeground(new Color(255,255,255));
                            command.setText("");
                            sudoProcedureOngoing=false;
                            isRoot=true;
                            receivedString = controller.call(sudocommand);
                            updateShell(receivedString);

                        }
                        else{
                            command.setText("");
                            shellArea.append("\nIncorrect Password : Try Again\n");
                        }
                    }
                    else if (command.getText().substring(userString.length()).equals("logout")) {
                        logout();
                    } else if (isLoginUserName) {
                        username = command.getText();
                        command.setText("");
                        shellArea.append("\n" + username);
                        shellArea.append("\nEnter Password:");
                        command.setCaretColor(new Color(0, 0, 0));
                        isLoginUserName = false;
                        isLoginPassword = true;
                    } else if (isLoginPassword) {
                        password = command.getText();
                        command.setText("");
                        command.setCaretColor(new Color(255, 255, 255));
                        isLoginPassword = false;
                        isLoginUserName = false;
                        password=new StringBuffer(password).reverse().deleteCharAt(0).toString();
                        login(username, password);
                        command.setForeground(new Color(255, 255, 255));
                    } else {
                        if (firstcommand) {
                            shellArea.setVisible(true);
                            firstcommand = false;
                        }
                        receivedString = controller.call(command.getText().substring(userString.length()));
                        updateShell(receivedString);
                    }
                    if(!sudoProcedureOngoing||!isLoginUserName)
                       command.setCaretPosition(userString.length());

                }
            }

        });

        JScrollPane scrollPane = new JScrollPane(shellPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        this.add(scrollPane);
    }

    private void updateShell(String receivedString) {
        shellArea.append("\n" + command.getText() + receivedString);
        command.setText(userString);
        callRobotBackspace();

    }

    private void callRobotBackspace() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
    }

    public void login(String username, String password) {

        ArrayList<User> userDetails;
        Map<String, String> constraints = new HashMap<String, String>();
        constraints.put("username", username);
        constraints.put("passwordhash", password);
        userDetails = ICommand.mongoConnect.getUsers(constraints);

        if (userDetails.size() != 0) {
            controller = new Controller(userDetails.get(0).getUsername(), userDetails.get(0));
            userString=userDetails.get(0).getUsername()+" $:";
            Controller.CURRENT_PATH=userDetails.get(0).getHome();

            shellArea.setText("");
            firstcommand = true;
            command.setText("");

            showUserString();
            callRobotBackspace();

        }
        else{
            shellArea.setText(SHELLTEXTINCORRECTLOGIN);
            callRobotBackspace();

            isLoginUserName = true;
            isLoginPassword = false;

        }

    }

    private void showUserString() {
        shellArea.append("Logged in successfully");
        command.append(userString);
        callRobotBackspace();
    }

    public ArrayList<String> logout() {
        Controller.CURRENT_PATH = "";
        shellArea.setText(SHELLTEXT);
        command.setText("");
        isLoginUserName=true;
        isLoginPassword=false;
        userString="";
        //Controller.CURRENT_USER = new User();
        //check username/pass and login

        return null;
    }

}
