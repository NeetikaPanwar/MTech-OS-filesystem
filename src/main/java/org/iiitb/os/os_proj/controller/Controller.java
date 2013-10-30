package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;

import org.iiitb.os.os_proj.commands.Cat;
import org.iiitb.os.os_proj.commands.Cd;
import org.iiitb.os.os_proj.commands.File;
import org.iiitb.os.os_proj.commands.Filesize;
import org.iiitb.os.os_proj.commands.Head;
import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.commands.Ls;
import org.iiitb.os.os_proj.commands.Mkdir;
import org.iiitb.os.os_proj.commands.Mv;
import org.iiitb.os.os_proj.commands.Pwd;
import org.iiitb.os.os_proj.commands.Rmdir;
import org.iiitb.os.os_proj.commands.Tail;
import org.iiitb.os.os_proj.commands.Touch;

public class Controller {

	private String cmd_list[] = { "cat", "cd", "file", "filesize", "head", "locate", "ls", "mkdir", "mv", 
			"pwd", "rmdir", "tail", "touch"};

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
		
		String split_cmd[] = cmd.split(" ");
		
		ArrayList<String> params = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();

		switch (cmd_val) {
		case 0:	Cat cat = new Cat();
				result = cat.runCommand(params);
				System.out.println(result);
				break;
				
		case 1:	Cd cd = new Cd();
				params.add(split_cmd[1]);
				result = cd.runCommand(params);
				System.out.println(result);
				break;
				
		case 2:	File file = new File();
				result = file.runCommand(params);
				System.out.println(result);
				break;
				
		case 3:	Filesize fs = new Filesize();
				result = fs.runCommand(params);
				System.out.println(result);
				break;
				
		case 4:	Head head = new Head();
				result = head.runCommand(params);
				System.out.println(result);
				break;
				
		case 5:	Locate locate = new Locate();
				result = locate.runCommand(params);
				System.out.println(result);
				break;
		
		case 6:	Ls ls = new Ls();
				result = ls.runCommand(params);
				System.out.println(result);
				break;
		
		case 7:	Mkdir mkdir = new Mkdir();
				result = mkdir.runCommand(params);
				System.out.println(result);
				break;
				
		case 8:	Mv mv = new Mv();
				result = mv.runCommand(params);
				System.out.println(result);
				break;
		
		case 9:	Pwd pwd = new Pwd();
				result = pwd.runCommand(params);
				System.out.println(result);
				break;
		
		case 10:Rmdir rmdir = new Rmdir();
				result = rmdir.runCommand(params);
				System.out.println(result);
				break;
		
		case 11:Tail tail = new Tail();
				result = tail.runCommand(params);
				System.out.println(result);
				break;
				
		case 12:Touch touch = new Touch();
				result = touch.runCommand(params);
				System.out.println(result);
				break;
				
		default:System.out.println("Command not found.");
		}

	}

	public static void main(String args[]) {

		new Controller("ls cde re");
	}

}
