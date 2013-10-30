package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

public class Filesize implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		int i = 0;
		int receivedFileSize;
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		MongoConnectivity fileSearch = new MongoConnectivity();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		receivedFile = fileSearch.getFiles(constraints);
		
		while(i<receivedFile.size())
		{
			receivedFileSize = receivedFile.get(i).getFile_size();
			i++;
		}
		if(receivedFile.size()==0)
		{
			result.add("failure");
			result.add("file does not exist");
			
		}
		//search in db for file
		//if exists, return success and filesize
		//else return failure
		return result;
	}

}
