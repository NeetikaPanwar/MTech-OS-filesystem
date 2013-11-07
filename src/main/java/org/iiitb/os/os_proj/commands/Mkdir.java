package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;
import com.mongodb.WriteResult;

import java.util.List;

public class Mkdir implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		
		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result=new ArrayList<String>();
		
		//Search if dir with same name already exists
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", path.get(0));		
		constraints.put("path", path.get(1));
		constraints.put("isDirectory", "true");
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);
		
		if (receivedFile.size() == 0) {
			//Create new userFile with isDirectory=true... Convert to DBObj... Add to DB
			UserFile usr = new UserFile();
			Date date = new Date();
			
			usr.setId(1234);
			usr.setName(path.get(0));
			usr.setFiletypeId(0);
			usr.setPath(path.get(1));
			usr.setFile_size(1234);
			usr.setTimestamp(date);
			usr.setDate_created(date);
			usr.setDate_updated(date);
			usr.setUser_created(1);
			usr.setUser_updated(1);
			usr.setDirectory(true);
			usr.setData(null);
			
			WriteResult ws = mongoConnect.createFile(usr);
			if(ws.getError() == null)
				result.add(ICommand.SUCCESS);
			else
			{
				result.add(ICommand.FAILURE);
				result.add("mkdir: cannot create directory '" + path.get(0) + "'");
			}

		} else {
			result.add(ICommand.FAILURE);
			result.add("mkdir: cannot create directory '" + path.get(0) + "': File exists");
		}
				
		//return success or failure depending on result
		return result;		
	}
	
}
