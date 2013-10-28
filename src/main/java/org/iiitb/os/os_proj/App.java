package org.iiitb.os.os_proj;

import java.util.ArrayList;

import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.shell.Shell;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Shell shell=new Shell();
        shell.setSize(500,500);
		shell.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		shell.setVisible(true);
		Locate l=new Locate();
		ArrayList<String> al=new ArrayList();
		al.add("Locate");
		al.add("kanchu17");
		l.runCommand(al);
		
    }
}
