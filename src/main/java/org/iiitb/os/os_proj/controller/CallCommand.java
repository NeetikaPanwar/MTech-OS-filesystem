package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

import org.iiitb.os.os_proj.db.MongoConnectivity;

public class CallCommand {
	
	public CallCommand(){
		
		MongoConnectivity mongoConnection = new MongoConnectivity();
		//mongoConnection.openConnection(MongoConnectivity.COLLECTION);
		
	}
	
	static String pwd(String path){
		return path;
	}
	
	static void cd(String path, String statement){
		
		if(statement.equals("cd"))
			System.out.println("Go to home directory.");
		else if(statement.equals("cd ."))
			System.out.println("Remain in same directory.");
		else if(statement.equals("cd .."))
			System.out.println("go to parent directory.");
		else if(statement.matches("cd (.)*"))
			System.out.println("go to child directory if it exists.");
		
	}
	
	static ArrayList<String> ls(String dir_path){
		
		ArrayList<String> file_list = new ArrayList<String>();
		
		//Search in db for this path and get the list of files
		
		
		
		//return to controller
		return file_list;
	}
	
	static void mkdir(String path, String dir_name){
		
	}
	
	static void rmdir(String path, String dir_name){
		
	}
	
	public static void main(String args[]){
		cd("","cd desktop");
		ls("/home/rajat");
	}

}
