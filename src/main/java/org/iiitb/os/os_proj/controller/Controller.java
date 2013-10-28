package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

public class Controller {

	private String cmd_list[] = { "pwd", "cd", "ls", "mkdir", "rmdir",
			"locate", "cat", "touch", "file", "filesize", "mv", "head", "tail" };

	public static String CURRENT_PATH = "";

	public Controller(String path) {		
		this.CURRENT_PATH = path;
	}

	private void call(String cmd) {

		int cmd_val = -1;

		for (int i = 0; i < cmd_list.length; i++) {

			if (cmd.matches(cmd_list[i] + "(.)*")) {

				cmd_val = i;
				break;
			}
		}

		switch (cmd_val) {
		case 0:	String path = CallCommand.pwd(CURRENT_PATH);
				System.out.println("pass path to shell through controller" + path);
				break;
				
		case 1:	CallCommand.cd(CURRENT_PATH, cmd);
				System.out.println("String path = pwd(); cd(path,statement);");
				break;
				
		case 2:	ArrayList<String> file_list = CallCommand.ls(CURRENT_PATH);
				System.out.println("Pass this list to shell to display." + file_list);
				break;
				
		case 3:	String split_cmd1[] = cmd.split(" ");	
				CallCommand.mkdir(CURRENT_PATH, split_cmd1[split_cmd1.length - 1]);
				break;
				
		case 4:	String split_cmd2[] = cmd.split(" ");	
				CallCommand.rmdir(CURRENT_PATH, split_cmd2[split_cmd2.length - 1]);
				break;
				
		default:System.out.println("Command not found.");
		}

	}

	public static void main(String args[]) {

		new Controller("ls cde re");
	}

}
