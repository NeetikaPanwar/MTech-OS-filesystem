package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class File implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		
		//search in db for file
		ArrayList<String> result = new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		constraints.put("path", Controller.CURRENT_PATH);
		//constraints.put("isDirectory", "false");
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);
		
		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add(params.get(0) + "\tERROR: cannot open '" + params.get(0) + "' (No such file or directory)");

		} else {
			result.add(ICommand.SUCCESS);
			result.add(Integer.toString(receivedFile.get(0).getFiletypeId()));
		}
		
//		while(i<receivedFile.size())
//		{
//			receivedFileTypeId = receivedFile.get(i).getFiletypeId();
//			i++;
//		}
//		if(receivedFile.size()==0)
//		{
//			result.add(ICommand.FAILURE);
//			result.add("file does not exist");
//			
//		}
		return result;
		
		//if exists, return success and filetype
		//else return failure
		
	}

}
