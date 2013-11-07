package org.iiitb.os.os_proj.utils;

import java.util.ArrayList;
import java.util.Arrays;

import org.iiitb.os.os_proj.controller.Controller;

public class GetPath {
	
	public static ArrayList<String> getSearchPath(String pathname){
			
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> split_path = new ArrayList<String>(Arrays.asList(pathname.split("/")));
		if(split_path.size() == 1)		{
			result.add(pathname);
			result.add(Controller.CURRENT_PATH);
		}
		else		{
			result.add(split_path.remove(split_path.size() - 1));
			result.add(split_path.toString());
		}
		
		return result;
	}

}
