package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.List;

//Not the cat's tail :(
public class Tail implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		//same as cat, but show only last 100 lines.If file is smaller, just show entire file
		return result;	
	}

}
