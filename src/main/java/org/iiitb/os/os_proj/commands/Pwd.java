package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pwd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		result.add(params.get(0));
		return result;
	}

}
