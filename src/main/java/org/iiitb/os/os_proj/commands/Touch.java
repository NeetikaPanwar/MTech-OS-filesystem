package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

import com.mongodb.WriteResult;

public class Touch implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		
		//Search if file exists
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("name", params.get(0));
		constraints.put("isDirectory", "false");
		ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);
		
		if(resFiles != null)	//File exists.. change the timestamp
		{
			resFiles.get(0).setTimestamp(new Date());
			result.add(ICommand.SUCCESS);
		}
		else	//create new userfile and add it to db
		{
			UserFile file = new UserFile();
			file.setId(12345);
			file.setName(params.get(0));
			file.setFiletypeId(123);
			file.setPath(Controller.CURRENT_PATH);
			file.setFile_size(1234);
			file.setTimestamp(new Date());
			file.setDate_created(new Date());
			//file.setDate_updated(null);
			file.setUser_created(1); //get userid
			//file.setUser_updated(null);
			file.setDirectory(false);
			//file.setData("");
			
			WriteResult createResult = mongoConnect.createFile(file);
			String error = createResult.getError();
			if(error.equals(null))
				result.add(ICommand.SUCCESS);
			else
			{
				result.add(ICommand.FAILURE);
				result.add("Unable to create new file.");
			}			
		}
	
		return result;
	}

}
