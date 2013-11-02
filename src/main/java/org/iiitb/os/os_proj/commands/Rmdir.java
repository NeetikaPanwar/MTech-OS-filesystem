package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;

import org.iiitb.os.os_proj.db.MongoConnectivity;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Rmdir implements ICommand {
	
	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		MongoConnectivity connect = new MongoConnectivity();
		DBCollection dbcollection;
		BasicDBObject searchDir = new BasicDBObject();
		searchDir.put("name", params.get(0));
		dbcollection = connect.openConnection(MongoConnectivity.COLLECTION);
		DBCursor cursor = dbcollection.find(searchDir);
		if(cursor.hasNext())
		{
			dbcollection.findAndRemove(cursor.next());
		}
		else
		{
			result.add("failure");
			result.add("directory does not exist");
		}
		//search if dir exists
		
		//if exists, remove from db
		//return success or failure message
		return result;
	}

}
