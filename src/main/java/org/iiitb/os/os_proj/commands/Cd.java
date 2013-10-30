package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;

import org.iiitb.os.os_proj.controller.Controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Cd implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		String searchPath = Controller.CURRENT_PATH;

		//Handle Cases .././null/directory
		if(params.get(0).equals(null))
			System.out.println("Go to home directory.");
		else if(params.get(0).equals("."));
			//System.out.println("stay in current path");
		else if(params.get(0).equals(".."))
			System.out.println("go to parent directory.");
		else
			searchPath = searchPath.concat("/" + params.get(0));		

		//Do Mongo Search Here
		BasicDBObject searchObject = new BasicDBObject();
		searchObject.put("path", searchPath);
		DBCursor cursor = mongoCollection.find(searchObject);
		
		if (cursor != null) {
			DBObject resObject = cursor.next();
			if(resObject.get("isDirectory").equals("TRUE"))
			{
				result.add(ICommand.SUCCESS);
	            result.add(searchPath);
			}
			else
			{
				result.add(ICommand.FAILURE);
	            result.add("Not a directory.");
			}            
            
        } else {
            result.add(ICommand.FAILURE);
            result.add("No such file or directory.");
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
