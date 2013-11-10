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
		Map<String, String> constraints = new HashMap<String, String>();
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
				Map<String, String> pathConstraint = new HashMap<String, String>();	
				pathConstraint.put("path", "/" + path.get(1) + "/" + path.get(0) + ".*/");
				ArrayList<UserFile> receivedFiles = mongoConnect.getFiles(constraints);
				for(UserFile u: receivedFiles)
					mongoConnect.deleteFile(u.getPath());
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
