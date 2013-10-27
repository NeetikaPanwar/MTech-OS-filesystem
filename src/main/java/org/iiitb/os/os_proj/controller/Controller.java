package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

public class Controller {

	private String cmd_list[] = { "ls", "cd", "pwd", "mkdir", "rmdir",
			"locate", "cat", "touch", "filesize", "mv", "head", "tail" };

	private String current_path = "";

	public Controller(String path) {		
		this.current_path = path;
	}

	public void call(String statement){

		String split_cmd[] = statement.split(" ", 2);

		System.out.println(split_cmd[0]);
		System.out.println(split_cmd[1]);

		find_cmd(split_cmd[0], statement);
	}

	private void find_cmd(String cmd, String statement) {

		int cmd_val = -1;

		for (int i = 0; i < cmd_list.length; i++) {

			if (cmd.equals(cmd_list[i])) {

				cmd_val = i;
				break;
			}
		}

		switch (cmd_val) {
		case 0:	ArrayList<String> file_list = CallCommand.ls(current_path);
				System.out.println("Pass this list to shell to display." + file_list);
				break;
				
		case 1:	CallCommand.cd(current_path, statement);
				System.out.println("String path = pwd(); cd(path,statement);");
				break;
				
		case 2:	String path = CallCommand.pwd(current_path);
				System.out.println("pass path to shell through controller" + path);
				break;
				
		case 3:	String split_cmd1[] = statement.split(" ");	
				CallCommand.mkdir(current_path, split_cmd1[split_cmd1.length - 1]);
				break;
				
		case 4:	String split_cmd2[] = statement.split(" ");	
				CallCommand.rmdir(current_path, split_cmd2[split_cmd2.length - 1]);
				break;
				
		default:System.out.println("Command not found.");
		}

	}

	public static void main(String args[]) {

		new Controller("ls cde re");
	}

}
