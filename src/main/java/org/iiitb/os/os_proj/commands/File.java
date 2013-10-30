package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

public class File implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		int i = 0;
		int receivedFileTypeId;
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		receivedFile = mongoConnect.getFiles(constraints);
		
		while(i<receivedFile.size())
		{
			receivedFileTypeId = receivedFile.get(i).getFiletypeId();
			i++;
		}
		if(receivedFile.size()==0)
		{
			result.add("failure");
			result.add("file does not exist");
			
		}
		return result;
		//search in db for file
		//if exists, return success and filetype
		//else return failure
		
	}

}
