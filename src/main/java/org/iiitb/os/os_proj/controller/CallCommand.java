package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

public class CallCommand {
	
	public CallCommand(){
		
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
		
		//Search in db for this path.
		String file_path = new String("Extract file path from db.");
		//file_path = "/home/rajat";
		
		
		//if dir_path matches then add name of file to the list of files in that directory
		if(file_path.matches(dir_path + "/(.)*")){
			String splitted_filepath[] = file_path.split("/");
			//System.out.println(splitted_filepath[splitted_filepath.length - 1]);
			file_list.add(splitted_filepath[splitted_filepath.length - 1]);
		}
		
		//return the list of files to the controller
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
