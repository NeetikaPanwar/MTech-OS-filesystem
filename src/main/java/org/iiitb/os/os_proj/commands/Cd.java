package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class Cd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		String searchPath = Controller.CURRENT_PATH;

		//Handle Cases .././null/directory
		if(params.get(0).equals(null))
			System.out.println("Controller.CURRENT_PATH = User.getHome()");
		else if(params.get(0).equals(".."))
			System.out.println("go to parent directory.");
		else
		{
			//Do Mongo Search Here
			Map<String, String> constraints = new HashMap<String, String>();
			constraints.put("path", searchPath);
			constraints.put("name", params.get(0));
			constraints.put("isDirectory", "TRUE");
			ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);

			if(resFiles != null)
			{
				result.add(ICommand.SUCCESS);
				result.add(searchPath + "/" + params.get(0));
			}
			else
			{
				result.add(ICommand.FAILURE);
				result.add("No such directory.");
			}
		}	

		//First index = success or failure
		return result;

	}

	//	public static void main(String arg[]){
	//		
	//		Cd cd  = new Cd();
	//		ArrayList<String> params= new ArrayList();
	//		params.add("rajat.txt");
	//		cd .runCommand(params);
	//	}

}
