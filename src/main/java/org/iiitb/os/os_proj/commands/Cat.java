package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

public class Cat implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
	
		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result = new ArrayList<String>();

		// search file exists in db
		// if exists, return file data
		
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", path.get(0));		
		constraints.put("path", path.get(1));
		constraints.put("isDirectory", false);
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add("cat: " + path.get(0) + ": no such file or directory");

		} else {
			result.add(ICommand.SUCCESS);
			result.add(receivedFile.get(0).getData());
		}
		result.add("cat is used to create, combine or display a text file on the screen.\nIt works by copying fileblocks of the given filename into buffer and then outputting the buffer contents.\nIf file does not exist, cat returns a FileNotFound error.");
		return result;
	}
}
