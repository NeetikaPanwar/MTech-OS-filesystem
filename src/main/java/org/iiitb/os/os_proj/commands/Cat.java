package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class Cat implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		// search file exists in db
		// if exists, return file data

		ArrayList<String> result = new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		constraints.put("isDirectory", "false");
		constraints.put("path", Controller.CURRENT_PATH);
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add("cat: " + params.get(0) + ": no such file or directory");

		} else {
			result.add(ICommand.SUCCESS);
			result.add(receivedFile.get(0).getData());
		}
		return result;
	}

}
