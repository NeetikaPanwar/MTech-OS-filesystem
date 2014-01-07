package org.iiitb.os.os_proj.commands;

import java.util.*;

import org.iiitb.os.os_proj.UserFile;

public class Locate implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();

		//Search db by name
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", params.get(0));
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if(receivedFile.size()>0){

			result.add(ICommand.SUCCESS);
			for(UserFile u: receivedFile)
				result.add(u.getPath() + u.getName());
		}
		else{
			result.add(ICommand.FAILURE);
			result.add("locate: " + params.get(0) + ": cannot be located");
		}
		result.add("locate is used to find the location of a file.\nIt works by recursively traversing the tree structure of the filesystem and checking file descriptors to check if filename matches the search term.");
		return result;
	}

}
