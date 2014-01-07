package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

//Not the cat's tail :(
public class Tail implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result=new ArrayList<String>();

		//Search if file exists
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", path.get(0));
		constraints.put("path", path.get(1));		
		constraints.put("isDirectory", false);
		ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);

		if(resFiles.size() != 0)	//File exists... display data
		{
			result.add(ICommand.SUCCESS);
			String data = resFiles.get(0).getData();
			String split_data[] = data.split("\n");
			if(split_data.length <= 100)
				result.add(data);
			else
			{
				String data_head = "";
				for(int i = (split_data.length - 100); i < split_data.length; i++)
					data_head += split_data[i];
					
				result.add(data_head);
			}			
		}
		else
		{
			result.add(ICommand.FAILURE);
			result.add("tail: " + path.get(0) + ": no such file or directory");
		}

		//same as cat, but show only last 100 lines.If file is smaller, just show entire file
		result.add("tail is used to display the ending of a file.\nThe kernel opens the file descriptor and uses fstat to check changes in the mtime value to show updates in the end of the file.");
		return result;	
	}

}
