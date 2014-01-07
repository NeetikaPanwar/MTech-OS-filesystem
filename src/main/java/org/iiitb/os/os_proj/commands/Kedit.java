package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.iiitb.os.os_proj.TextEditor;
import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

public class Kedit implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {

		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result = new ArrayList<String>();

		// search file exists in db
		// if exists, return file data

		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", path.get(0));		
		constraints.put("path", path.get(1));
		constraints.put("isDirectory", false);
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);

		if (receivedFile.size() == 0) {	//file does not exist..open kedit with the filename
			//creating new file
			UserFile usr = new UserFile();
			Date date = new Date();

			long range = 1234567L;
			Random r = new Random();
			long number = (long)(r.nextDouble()*range);

			usr.setId(number);
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

			mongoConnect.createFile(usr);
			new TextEditor(usr, true);

		} 
		else 	//file exists..open file in editor with data
			new TextEditor(receivedFile.get(0), true);

		result.add(ICommand.SUCCESS);
		return result;
	}
}
