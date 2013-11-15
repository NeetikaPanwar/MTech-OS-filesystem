package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;


public class Rmdir implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result=new ArrayList<String>();

		//search if dir exists
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", path.get(0));		
		constraints.put("path", path.get(1));
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		//if exists, remove from db
		//remove all directories and files in that dir recursively and then delete it
		if(receivedFile.size() == 0){
			result.add(ICommand.FAILURE);
			result.add("rmdir: failed to remove'" + path.get(0) + "': No such file or directory");
		}
		else
		{
			if(receivedFile.get(0).isDirectory())
			{
				//recursively call deletefile
				Map<String, Object> pathConstraint = new HashMap<String, Object>();	
				String pathPattern = path.get(1) + path.get(0);
				pathConstraint.put("path", java.util.regex.Pattern.compile(pathPattern));
				ArrayList<UserFile> receivedFiles = mongoConnect.getFiles(pathConstraint);
				for(UserFile u: receivedFiles)
					mongoConnect.deleteFile(u.getName(), u.getPath());
				mongoConnect.deleteFile(path.get(0), path.get(1));
				result.add(ICommand.SUCCESS);
			}
			else
			{
				result.add(ICommand.FAILURE);
				result.add("rmdir: failed to remove'" + path.get(0) + "': Not a directory");
			}			
		}

		return result;
	}

}
