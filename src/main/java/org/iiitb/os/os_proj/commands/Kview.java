package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.ImageViewer;
import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

public class Kview implements ICommand{

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

		if (receivedFile.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add("kview: " + path.get(0) + ": no such file or directory");

		} else {
			//call image viewer to display the image
			new ImageViewer(receivedFile.get(0));
			result.add(ICommand.SUCCESS);
		}
		return result;
	}
}
