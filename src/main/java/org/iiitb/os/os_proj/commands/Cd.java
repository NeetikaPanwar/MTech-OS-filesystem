package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;
import org.iiitb.os.os_proj.utils.GetPath;

public class Cd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();

		//Handle Cases .././null/directory
//		if(params.get(0).equals(null))
//		{
//			result.add(ICommand.SUCCESS);
//			System.out.println("Controller.CURRENT_PATH = User.getHome()");
//
//		}
//		else 
			if(params.get(0).equals(".."))
		{
			System.out.println("go to parent directory.");
			ArrayList<String> split_path = new ArrayList<String>(Arrays.asList(Controller.CURRENT_PATH.split("/")));
			split_path.remove(split_path.size() - 1);
			result.add(ICommand.SUCCESS);
			String path = "";
			for(String s: split_path)
				path += s + "/";
			Controller.CURRENT_PATH = path;
		}
		else
		{
			ArrayList<String> path = GetPath.getSearchPath(params.get(0));		

			//Do Mongo Search Here
			Map<String, Object> constraints = new HashMap<String, Object>();
			constraints.put("name", path.get(0));
			constraints.put("path", path.get(1));
			constraints.put("isDirectory", true);
			ArrayList<UserFile> receievedFile = mongoConnect.getFiles(constraints);

			if(receievedFile.size() != 0)	//Path exists
			{
				result.add(ICommand.SUCCESS);
				Controller.CURRENT_PATH += path.get(0) + "/";
			}
			else
			{
				result.add(ICommand.FAILURE);
				result.add("cd: " + path.get(0) + ": no such file or directory");
			}
		}	

		return result;
	}

}
