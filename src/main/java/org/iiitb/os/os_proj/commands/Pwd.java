package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.List;

import org.iiitb.os.os_proj.controller.Controller;

public class Pwd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		result.add(ICommand.SUCCESS);
		result.add(Controller.CURRENT_PATH);
		return result;
	}

}
