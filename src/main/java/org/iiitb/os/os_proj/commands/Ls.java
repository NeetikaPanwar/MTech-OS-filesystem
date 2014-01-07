package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;

public class Ls implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		ArrayList<String> result = new ArrayList<String>();
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("path", params.get(0));
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() != 0) {
			result.add(ICommand.SUCCESS);
			for(UserFile u: receivedFile)
				result.add(u.getName());
		} 
		else {
			result.add(ICommand.FAILURE);
		}
		result.add("ls command is used to list the contents of a directory.\n It simply reads the file descriptor of the files and directories within the given directory and outputs them as a list.\nDirectory entries are gotten using the getdents() function.");
		return result;
	}

}

