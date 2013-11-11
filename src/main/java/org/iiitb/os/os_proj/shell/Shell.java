package org.iiitb.os.os_proj.shell;

import org.iiitb.os.os_proj.User;
import org.iiitb.os.os_proj.commands.ICommand;
import org.iiitb.os.os_proj.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;

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
    private static String SHELLTEXT = "\t              Welcome to KanchuFS Shell \nEnter Username:";
    private boolean isLoginUserName = true;
    private boolean isLoginPassword = false;
    private boolean firstcommand=false;

    private String username;
    private String password;

    private JTextArea shellArea;
    private JTextArea command;

    private Border border;


    private Controller controller;

    public Shell() {

        controller = new Controller("", "");

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
        command.setCaretColor(new Color(255,255,255));
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (command.getText().equals("logout")) {
                        logout();
                    } else if (isLoginUserName) {
                        username = command.getText();
                        command.setText("");
                        shellArea.append("\n" + username);
                        shellArea.append("\nEnter Password:");
                        command.setCaretColor(new Color(0,0,0));
                        isLoginUserName = false;
                        isLoginPassword = true;
                    } else if (isLoginPassword) {
                        password = command.getText();
                        command.setText("");
                        command.setCaretColor(new Color(255,255,255));
                        isLoginPassword = false;
                        isLoginUserName = false;
                        login(username, password);
                        command.setForeground(new Color(255, 255, 255));
                    } else {
                        if(firstcommand){
                            shellArea.setVisible(true);
                            firstcommand=false;
                        }
                        controller.call(command.getText());
                    }

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


//    private void refresh() {
//        shellArea.setText(SHELLTEXT);
//        command.append(userString);
//        command.setCaretPosition(command.getText().length());
//        repaint();
//    }

    public void login(String username, String password) {

        ArrayList<User> userDetails;
        Map<String, String> constraints = new HashMap<String,String>();
        constraints.put("name", username);
        constraints.put("password", password);
        userDetails = ICommand.mongoConnect.getUsers(constraints);

//       if (userDetails.size() != 0) {
//            controller = new Controller(userDetails.get(0).getUsername(), userDetails.get(0).getHome());
//            userString=userDetails.get(0).getUsername()+" $:";
//            Controller.CURRENT_PATH=userDetails.get(0).getHome();
//        }

        userString="abcd $:" ;
        Controller.CURRENT_PATH="Home";

        shellArea.setText("");
        firstcommand=true;
        //shellArea.setVisible(false);
        command.setText("");

        showUserString();

    }

    private void showUserString() {
        shellArea.append("Logged in successfully");
        command.append(userString);
        Robot robot= null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        // command.setCaretPosition(command.getText().length());

    }

    public ArrayList<String> logout() {
        Controller.CURRENT_PATH = "";
        Controller.CURRENT_USER = "";
        //check username/pass and login

        return null;
    }

}
