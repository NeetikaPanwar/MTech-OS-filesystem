package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class Ls implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		ArrayList<String> result = new ArrayList<String>();
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("path", Controller.CURRENT_PATH);
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() != 0) {
			result.add(ICommand.SUCCESS);
			for(UserFile u: receivedFile)
				result.add(u.getName());
		} 
		else {
			result.add(ICommand.FAILURE);
		}

		return result;
	}

}

