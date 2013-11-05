package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.List;

public class Pwd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		result.add(params.get(0));
		return result;
	}

}
