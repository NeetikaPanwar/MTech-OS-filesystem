package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;

public class Filesize implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add(params.get(0) + "\tERROR: cannot open '" + params.get(0) + "' (No such file or directory)");

		} else {
			result.add(ICommand.SUCCESS);
			result.add(Long.toString(receivedFile.get(0).getFile_size()));

		}
		// search in db for file
		// if exists, return success and filesize
		// else return failure
		return result;
	}

}
