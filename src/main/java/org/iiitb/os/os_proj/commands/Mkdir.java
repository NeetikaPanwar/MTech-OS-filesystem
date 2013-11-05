package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.db.MongoConnectivity;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

import java.util.List;

public class Mkdir implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		String error = null;
		
		MongoConnectivity connect = new MongoConnectivity(MongoConnectivity.DATABASE);
	
		ArrayList<String> result=new ArrayList<String>();
		BasicDBObject searchDir = new BasicDBObject();
		DBObject NewDirectory = new BasicDBObject();
		searchDir.put("name", params.get(0));
		DBCursor cursor = connect.openConnection(MongoConnectivity.COLLECTION).find(searchDir);
		if(cursor.hasNext())
		{
			result.add(ICommand.FAILURE);
			result.add(" file already exists");
		}
		else
		{
			UserFile MakeFile = new UserFile();
			UserFile MadeFile =	 makeUserFile(MakeFile);
				WriteResult result1 = connect.createFile(MadeFile);
				error = result1.getError();
		}
		if(error.equals(null))
		{
			result.add("failure");
		    result.add("could not create a new directory");
		}
		//Search if dir with same name already exists
		//if No:
		//Create new userFile with isDirectory=true... Convert to DBObj... Add to DB
		
		//return success or failure depending on result
		return result;
		
	}
	public UserFile makeUserFile(UserFile testFile)
	{
		Date date = new Date();
		
		testFile.setData("This is a new directory");
		testFile.setDate_created(date);
		testFile.setDate_updated(date);
		testFile.setFile_size(1234);
		testFile.setId(1234);
		testFile.setFiletypeId(1);
		testFile.setId(1234);
		testFile.setName("Neetika");
		testFile.setPath("/home/neetika");
		testFile.setTimestamp(date);
		testFile.setDirectory(true);
		testFile.setUser_created(1);
		testFile.setUser_updated(2);
		return testFile;
	}
	
}
