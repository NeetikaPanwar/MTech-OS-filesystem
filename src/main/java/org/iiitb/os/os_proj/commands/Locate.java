package org.iiitb.os.os_proj.commands;

import java.util.*;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

public class Locate implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		int j= 0;
		//Search db by name
		MongoConnectivity testMongo=new MongoConnectivity(MongoConnectivity.DATABASE);
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(0));
		ArrayList<UserFile> files = testMongo.getFiles(constraints);
		if(files.size()>0){
		
		result.add(ICommand.SUCCESS);
		result.add(files.get(0).getPath());

		}
		else{
		result.add(ICommand.FAILURE);
		result.add("file does not located");
		}
		System.out.println(result);
		return result;
	}

}
