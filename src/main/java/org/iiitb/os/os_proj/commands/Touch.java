package org.iiitb.os.os_proj.commands;

import java.util.*;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

import com.mongodb.WriteResult;

public class Touch implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> path = GetPath.getSearchPath(params.get(0));
		ArrayList<String> result=new ArrayList<String>();
		
		//Search if file exists
		Map<String, Object> constraints = new HashMap<String, Object>();
		constraints.put("name", path.get(0));		
		constraints.put("path", path.get(1));
		constraints.put("isDirectory", false);
		ArrayList<UserFile> receivedFile = mongoConnect.getFiles(constraints);
		
		if(receivedFile.size()!=0)	//File exists.. change the timestamp
		{
			receivedFile.get(0).setTimestamp(new Date());
			result.add(ICommand.SUCCESS);
		}
		else	//create new userfile and add it to db
		{
			UserFile file = new UserFile();
			Date date = new Date();

            long range = 123456L;
            Random r = new Random();
            long number = (long)(r.nextDouble()*range);

			file.setId(number);
			file.setName(path.get(0));
			file.setFiletypeId(1);
			file.setPath(path.get(1));
			file.setFile_size(1234);
			file.setTimestamp(date);
			file.setDate_created(date);
			file.setDate_updated(date);
			file.setUser_created(1); //get userid
			file.setUser_updated(1);
			file.setDirectory(false);
			file.setData(null);
			
			WriteResult createResult = mongoConnect.createFile(file);
			if(createResult.getError()==null)
				result.add(ICommand.SUCCESS);
			else
			{
				result.add(ICommand.FAILURE);
				result.add("touch: Unable to create new file.");
			}			
		}	
		return result;
	}


}
