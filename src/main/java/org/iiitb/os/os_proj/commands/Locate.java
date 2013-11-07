package org.iiitb.os.os_proj.commands;

import java.util.*;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetFileType;

public class Locate implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();

		//Search db by name
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if(receivedFile.size()>0){

			result.add(ICommand.SUCCESS);
			for(UserFile u: receivedFile)
				result.add(u.getPath() + u.getName() + "." + GetFileType.getFileExt(u.getFiletypeId()));
		}
		else{
			result.add(ICommand.FAILURE);
			result.add("locate: " + params.get(0) + ": cannot be located");
		}
		return result;
	}

}
