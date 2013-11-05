package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;

public class Head implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();

		//Search if file exists
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("name", params.get(0));
		constraints.put("isDirectory", "false");
		ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);
		
		if(resFiles != null)	//File exists... display data
		{
			String data = resFiles.get(0).getData();
			String split_data[] = data.split("\n");
			if(split_data.length <= 100)
				result.add(data);
			else
			{
				String data_head = "";
				for(int i = 0; i < 100; i++)
					data_head += split_data[i];
					
				result.add(data_head);
			}				
			result.add(ICommand.SUCCESS);
		}
		else
		{
			result.add(ICommand.FAILURE);
			result.add("No such file exists.");
		}

		//same as cat, but return only first 100 lines. If file is smaller, just show entire file
		return result;	
	}

}
