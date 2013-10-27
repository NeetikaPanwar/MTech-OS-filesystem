package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

public class Controller {

	private String cmd_list[] = { "pwd", "cd", "ls", "mkdir", "rmdir",
			"locate", "cat", "touch", "file", "filesize", "mv", "head", "tail" };

	private String current_path = "";

	public Controller(String path) {		
		this.current_path = path;
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
		case 0:	String path = CallCommand.pwd(current_path);
				System.out.println("pass path to shell through controller" + path);
				break;
				
		case 1:	CallCommand.cd(current_path, cmd);
				System.out.println("String path = pwd(); cd(path,statement);");
				break;
				
		case 2:	ArrayList<String> file_list = CallCommand.ls(current_path);
				System.out.println("Pass this list to shell to display." + file_list);
				break;
				
		case 3:	String split_cmd1[] = cmd.split(" ");	
				CallCommand.mkdir(current_path, split_cmd1[split_cmd1.length - 1]);
				break;
				
		case 4:	String split_cmd2[] = cmd.split(" ");	
				CallCommand.rmdir(current_path, split_cmd2[split_cmd2.length - 1]);
				break;
				
		default:System.out.println("Command not found.");
		}

	}

	public static void main(String args[]) {

		new Controller("ls cde re");
	}

}
