package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;

public class Cd implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		String searchPath=params.get(0);
		//Handle Cases .././null/directory
		
		//Do Mongo Search Here
		
		//First index= success or failure
		return result;
		
	}

}
