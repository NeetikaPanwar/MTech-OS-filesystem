package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class Mv implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		//get 1st and 2nd parameter
		int i=0;
		String source=params.get(0);
		String destination=params.get(1);
		MongoConnectivity testMongo=new MongoConnectivity();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", source);
		Map<String, String> constraints1 = new HashMap<String, String>();
		constraints.put("name", destination);
		ArrayList<UserFile> files = testMongo.getFiles(constraints);
		ArrayList<UserFile> files1 = testMongo.getFiles(constraints1);
		if(files.size()>0&&files1.size()>0){
			while(i<files.size()){
				String sourceData=files.get(i).getData();
				String destinationData=sourceData;

				//updateCommon(files1);
			result.add("success");	
			}
		}
		
		else if(files.size()>0&&files1.size()<0)
			{while(i<files.size()){
				String sourceData=files.get(i).getData();
				String destinationData=sourceData;
				UserFile u=new UserFile();
				u.setName("destination");
				u.setData(destinationData);
				u.setDate_created(null);
				u.setDate_updated(null);
				u.setFile_size(0);
				u.setFiletypeId(1);
				u.setPath("/kanchu/desktop");
				u.setData(sourceData);
				
			WriteResult wr = testMongo.createFile(u);
			if(wr.getError().equals(null))
				
			
				result.add("success");
				}
			}
		
		else{
			result.add("failure");
		result.add("file does not located");
			
		}
			
		System.out.println("result");
		//search both paths for existence
		//If even one doesn't exist, return error
		//else
		//Update paths with regex, and change parent paths (3:28 PM idea)
		return result;
	}

}
