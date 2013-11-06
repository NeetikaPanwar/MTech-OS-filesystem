package org.iiitb.os.os_proj.shell;

import org.iiitb.os.os_proj.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Shell extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROWS = 26;
    public static final int COLUMNS = 43;
    public static final int LINE_LENGTH = 407;
    public static String userString = "/Navin:";

    private JScrollPane scrollPane;
    private JPanel shellPanel;
    private JTextArea shellArea;
    private JTextArea command;
    private String shellText;

    private Controller controller;

    public Shell() {

        controller = new Controller("","");

        shellPanel = new JPanel();
        shellPanel.setBackground(new Color(0, 0, 0));

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        shellText = " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does n    ot what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
//                " LineMetrics does not what I want...\n" +
                " LineMetrics does not what I want...\n" +
                " LineMetrics does not what I want...\n" +
                " LineMetrics does not what I want...\n I've looked into it a hundred times. Can I LineMetrics does not what I want... I've looked into it a hundred times. Can I check";
        shellArea = new JTextArea(ROWS, COLUMNS);
        shellArea.setText(shellText);
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (command.getText().equals("login")) {
                        login_routine();
                    } else if (command.getText().equals("logout")) {
                        logout();
                    } else {
                        controller.call(command.getText());
                    }
                }
            }

        });

        scrollPane = new JScrollPane(shellPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());

        this.add(scrollPane);
    }

    private void login_routine() {
        shellText = shellText.concat("\nEnter Username:");
        refresh();
    }

    private void refresh(){
        shellArea.setText(shellText);
        command.append(userString);
        command.setCaretPosition(command.getText().length());
        repaint();
    }

    public ArrayList<String> login(String username, String password) {
        //check username/pass and login

        return null;
    }

    public ArrayList<String> logout() {
        //check username/pass and login

        return null;
    }

}
