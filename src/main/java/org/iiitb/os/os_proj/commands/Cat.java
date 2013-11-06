package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;

// Not the billi walla cat... Please note.
public class Cat implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		// search file exists in db
		// if exists, return file data

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		constraints.put("isDirectory", "false");
		constraints.put("path", "/home/kanchan");
		receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add("File cannot be found");

		} else {
			{
				result.add(ICommand.SUCCESS);
				result.add(receivedFile.get(0).getData());

			}
		}

		return result;
	}

}
