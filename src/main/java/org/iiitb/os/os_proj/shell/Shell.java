package org.iiitb.os.os_proj.shell;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class Shell extends JFrame {

	JPanel shellPanel;
	JTextArea shellArea;
	JTextArea command;

	public Shell() {
		shellPanel = new JPanel();
		shellPanel.setBackground(new Color(0, 0, 0));

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        String threeline="LineMetrics does not what I want... I've looked into it a hundred times. Can I LineMetrics does not what I want... I've looked into it a hundred times. Can I check";
		shellArea = new JTextArea(26,43);
        shellArea.setText(threeline);
        shellArea.setVisible(true);
        shellArea.setLineWrap(true);
        shellArea.setWrapStyleWord(true);
        shellArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(5, 10, 0, 10)));
		shellArea.setEditable(false);
		shellArea.setBackground(new Color(0, 0, 0));
		shellArea.setForeground(new Color(255, 255, 255));

		command = new JTextArea(5, 43);
		command.setForeground(new Color(255, 255, 255));
        command.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(0, 10, 5, 10)));
		command.setBackground(new Color(0, 0, 0));
		command.requestFocus();

		shellPanel.add(shellArea);
        shellPanel.add(command);

        Font font = new Font("Arial",Font.PLAIN,12);
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(font);
        int lines=fm.stringWidth(shellArea.getText())/407;

        shellArea.setRows(lines);
        revalidate();

        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                command.requestFocusInWindow();
            }
        });

        this.add(shellPanel);
    }

}
