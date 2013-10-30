package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;

public class Ls implements ICommand {

	public ArrayList<String> runCommand(ArrayList<String> params) {
		ArrayList<String> result = new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", params.get(0));
		ArrayList<UserFile> resFiles = mongoConnect.getFiles(constraints);

		if (resFiles != null) {
			result.add(ICommand.SUCCESS);
		} else {
			result.add(ICommand.FAILURE);
		}

		while(resFiles != null){
			for(int i=0; i < (resFiles.size()-1); i++)				
				result.add(resFiles.get(i).getName());
		}
		return result;
	}


	//    public static void main(String[] args) {
	//        ArrayList<String> a=new ArrayList<String>();
	//        a.add("/home/kancha");
	//        ICommand ls=new Ls();
	//        ArrayList<String> b=ls.runCommand(a);
	//        for(String s: b){
	//            System.out.println(s);
	//        }
	//    }
}

