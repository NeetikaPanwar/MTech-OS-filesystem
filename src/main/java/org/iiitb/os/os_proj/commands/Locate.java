package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

public class Locate implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		int i = 0;
		//Search db by name
		MongoConnectivity testMongo=new MongoConnectivity(MongoConnectivity.DATABASE);
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", params.get(1));
		ArrayList<UserFile> files = testMongo.getFiles(constraints);
		if(files.size()>0){
		while(i<files.size())
		{
		result.add(files.get(i).getPath());
		}
		}
		else{
		result.add("failure");
		result.add("file does not located");
		}
		System.out.println(result);
		return result;
	}

}
