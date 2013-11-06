package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class Cd implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();

		//Handle Cases .././null/directory
		if(params.get(0).equals(null))
		{
			result.add(ICommand.SUCCESS);
			System.out.println("Controller.CURRENT_PATH = User.getHome()");
			
		}
		else if(params.get(0).equals(".."))
		{
			System.out.println("go to parent directory.");
			String split_path[] = Controller.CURRENT_PATH.split("/");
			String path = "";
			for(int i = 0; i < split_path.length - 1; i++)
				path = path + "/" + split_path[i];			
			result.add(ICommand.SUCCESS);
			Controller.CURRENT_PATH = path;
		}

		else
		{
			//Do Mongo Search Here
			Map<String, String> constraints = new HashMap<String, String>();
			constraints.put("path", Controller.CURRENT_PATH);
			constraints.put("name", params.get(0));
			constraints.put("isDirectory", "true");
			ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);

			if(resFiles != null)	//Path exists
			{
				result.add(ICommand.SUCCESS);
				Controller.CURRENT_PATH += "/" + params.get(0);
			}
			else
			{
				result.add(ICommand.FAILURE);
				result.add("cd: " + params.get(0) + ": no such file or directory");
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
