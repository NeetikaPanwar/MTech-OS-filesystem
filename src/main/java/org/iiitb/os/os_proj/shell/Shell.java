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
    private static String SHELLTEXT="\t              Welcome to KanchuFS Shell \nEnter Username:";
    private boolean isLoginUserName=true;
    private boolean isLoginPassword=false;

    private String username;
    private String password;

    private JScrollPane scrollPane;
    private JPanel shellPanel;
    private JTextArea shellArea;
    private JTextArea command;


    private Controller controller;

    public Shell() {

        controller = new Controller("","");

        shellPanel = new JPanel();
        shellPanel.setBackground(new Color(0, 0, 0));

        Border border = BorderFactory.createLineBorder(Color.BLACK);

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

                if(isLoginPassword){
                    command.setForeground(new Color(0,0,0));
                    command.setCaretPosition(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (command.getText().equals("logout")) {
                        logout();
                    } else {
                        if(isLoginUserName){
                           username=command.getText();
                            command.setText("");
                            shellArea.append("\n"+username);
                            shellArea.append("\nEnter Password:");
                            isLoginUserName=false;
                            isLoginPassword=true;
                        }
                        else if(isLoginPassword){
                            password=command.getText();
                            command.setText("");
                            isLoginPassword=false;
                            isLoginUserName=false;
                            login(username,password);
                            command.setForeground(new Color(255, 255, 255));
                        }
                        else{
                        controller.call(command.getText());
                        }
                    }

                    command.setCaretPosition(0);

                }
            }

        });

        scrollPane = new JScrollPane(shellPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        this.add(scrollPane);
    }


    private void refresh(){
        shellArea.setText(SHELLTEXT);
        command.append(userString);
        command.setCaretPosition(command.getText().length());
        repaint();
    }

    public ArrayList<String> login(String username, String password) {

        System.out.println(username+ "Hello" +password);
        String userName, path;
    	ArrayList<User> userDetails = new ArrayList<User>();
    	Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", username);
		constraints.put("name", password);
		userDetails = ICommand.mongoConnect.getUsers(constraints);
	
		if(userDetails.size()!=0)
		{Controller controller = new Controller(userDetails.get(0).getUsername(), userDetails.get(0).getHome());
		}
        return null;
    }

    public ArrayList<String> logout() {
    	Controller.CURRENT_PATH	= "";
		Controller.CURRENT_USER = "";
    	//check username/pass and login

        return null;
    }

}
