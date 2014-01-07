package org.iiitb.os.os_proj;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

public class TextEditor extends JFrame implements ActionListener
{
private JTextArea textArea;

private JMenuBar menuBar;
private JMenu fileM,editM,viewM;
private JScrollPane scpane;
private JMenuItem exitI,cutI,copyI,pasteI,selectI,saveI;
private String content;
private JToolBar toolBar;
private String dataToSave;
private ArrayList<UserFile> files;
private UserFile user_file;
public TextEditor(UserFile u, boolean readOnly)
{
	 super("TextEditor");
	
   String dataFromFile = u.getData();
    user_file = returnUserFile(u);
    setSize(600, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    Container pane = getContentPane();
    pane.setLayout(new BorderLayout());

    
    content = " ";
    textArea = new JTextArea();
    textArea.setText(dataFromFile);           //textarea
    menuBar = new JMenuBar(); //menubar
    fileM = new JMenu("File"); //file menu
    editM = new JMenu("Edit"); //edit menu
 
    scpane = new JScrollPane(textArea); //scrollpane  and add textarea to scrollpane
    exitI = new JMenuItem("Exit");
    cutI = new JMenuItem("Cut");
    copyI = new JMenuItem("Copy");
    pasteI = new JMenuItem("Paste");
    selectI = new JMenuItem("Select All"); //menuitems
    saveI = new JMenuItem("Save"); //menuitems
 
   
    toolBar = new JToolBar();

    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);

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
public UserFile returnUserFile(UserFile u)
{
	return u;
}
public void actionPerformed(ActionEvent e) 
{
	
    JMenuItem choice = (JMenuItem) e.getSource();
    if (choice == saveI)
    {
      dataToSave = textArea.getText();
      user_file.setData(dataToSave);
    }
    else if (choice == exitI)
        this.dispose();
    else if (choice == cutI)
    {
        content = textArea.getSelectedText();
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
    }
    else if (choice == copyI)
        content = textArea.getSelectedText();
    else if (choice == pasteI)
        textArea.insert(content, textArea.getCaretPosition());
    else if (choice == selectI)
        textArea.selectAll();
   
}
/*public static void main(String[] args) 
{
	String file_name = "Kanchu17";
	String file_path = "/home/kanchan";
    new TextEditor();
}*/

}