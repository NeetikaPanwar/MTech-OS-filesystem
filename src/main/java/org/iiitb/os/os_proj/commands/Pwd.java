package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.os.os_proj.controller.Controller;

public class Pwd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		result.add(ICommand.SUCCESS);
		result.add(Controller.CURRENT_PATH);
		result.add("pwd is used to output the name of the current working directory.\nUpon login the working directory is set to your home directory.\npwd works by outputting the result of the lstat command which gets the current directory in the filesystem.");
		return result;
	}

}
