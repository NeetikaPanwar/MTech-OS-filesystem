package org.iiitb.os.os_proj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextEditor extends JFrame implements ActionListener
{
private JTextArea ta;

private JMenuBar menuBar;
private JMenu fileM,editM,viewM;
private JScrollPane scpane;
private JMenuItem exitI,cutI,copyI,pasteI,selectI,saveI;
private String content;
private JToolBar toolBar;
public TextEditor()
{
    super("TextEditor");
    setSize(600, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = getContentPane();
    pane.setLayout(new BorderLayout());

    
    content = " ";
    ta = new JTextArea(); //textarea
    menuBar = new JMenuBar(); //menubar
    fileM = new JMenu("File"); //file menu
    editM = new JMenu("Edit"); //edit menu
 
    scpane = new JScrollPane(ta); //scrollpane  and add textarea to scrollpane
    exitI = new JMenuItem("Exit");
    cutI = new JMenuItem("Cut");
    copyI = new JMenuItem("Copy");
    pasteI = new JMenuItem("Paste");
    selectI = new JMenuItem("Select All"); //menuitems
    saveI = new JMenuItem("Save"); //menuitems
 
   
    toolBar = new JToolBar();

    ta.setLineWrap(true);
    ta.setWrapStyleWord(true);

    setJMenuBar(menuBar);
    menuBar.add(fileM);
    menuBar.add(editM);
   

    fileM.add(saveI);
   
    fileM.add(exitI);

    editM.add(cutI);
    editM.add(copyI);
    editM.add(pasteI);        
    editM.add(selectI);

   


   

    pane.add(scpane,BorderLayout.CENTER);
    pane.add(toolBar,BorderLayout.SOUTH);

    saveI.addActionListener(this);
   
    exitI.addActionListener(this);
    cutI.addActionListener(this);
    copyI.addActionListener(this);
    pasteI.addActionListener(this);
    selectI.addActionListener(this);
   
    setVisible(true);
}
public void actionPerformed(ActionEvent e) 
{
    JMenuItem choice = (JMenuItem) e.getSource();
    if (choice == saveI)
    {
      
    }
    else if (choice == exitI)
        System.exit(0);
    else if (choice == cutI)
    {
        content = ta.getSelectedText();
        ta.replaceRange("", ta.getSelectionStart(), ta.getSelectionEnd());
    }
    else if (choice == copyI)
        content = ta.getSelectedText();
    else if (choice == pasteI)
        ta.insert(content, ta.getCaretPosition());
    else if (choice == selectI)
        ta.selectAll();
   
}
public static void main(String[] args) 
{
    new TextEditor();
}
}