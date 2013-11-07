package org.iiitb.os.os_proj.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.iiitb.os.os_proj.commands.Cat;
import org.iiitb.os.os_proj.commands.Cd;
import org.iiitb.os.os_proj.commands.File;
import org.iiitb.os.os_proj.commands.Filesize;
import org.iiitb.os.os_proj.commands.Head;
import org.iiitb.os.os_proj.commands.ICommand;
import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.commands.Ls;
import org.iiitb.os.os_proj.commands.Mkdir;
import org.iiitb.os.os_proj.commands.Mv;
import org.iiitb.os.os_proj.commands.Pwd;
import org.iiitb.os.os_proj.commands.Rmdir;
import org.iiitb.os.os_proj.commands.Tail;
import org.iiitb.os.os_proj.commands.Touch;

public class Controller {

	private enum commands {
		cat, cd, file, filesize, head, locate, ls, mkdir, mv, pwd, rmdir, tail, touch
	}

	public static String CURRENT_PATH = "";
	public static String CURRENT_USER = "";

	public Controller(String path, String user) {
		this.CURRENT_PATH = path;
		this.CURRENT_USER = user;
	}

	public void call(String cmd) {

		cmd = cmd.trim();
		List<String> params = new LinkedList<String>(Arrays.asList(cmd
				.split(" ")));
		String split_cmd = params.remove(0);

		// ArrayList<String> params = ;
		ArrayList<String> result = new ArrayList<String>();

		switch (commands.valueOf(split_cmd)) {
		case cat: 
			if(params.size() == 1)
			{
				Cat cat = new Cat();
				result = cat.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: " + result.get(1));
			}
			else
				System.out.println("Pass this to shell: cat: Incorrect no of arguments.");
			break;

		case cd: // in cd only first parameter is used, others are ignored
			Cd cd = new Cd();
			result = cd.runCommand(params);
			if(result.get(0).equals(ICommand.SUCCESS))
				System.out.println("Pass current path to shell: " + CURRENT_PATH);
			else	//pass msg failure
				System.out.println("Pass this to shell: " + result.get(1));
			break;

		case file:	
			if(params.size() == 1)
			{
				File file = new File();
				result = file.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: " + result.get(1));
			}
			else
				System.out.println("Pass this to shell: file: Incorrect no of arguments.");
			break;

		case filesize:
			if(params.size() == 1)
			{
				Filesize fs = new Filesize();
				result = fs.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: " + result.get(1));
			}
			else
				System.out.println("Pass this to shell: filesize: Incorrect no of arguments.");
			break;

		case head:
			if(params.size() == 1)
			{
				Head head = new Head();
				result = head.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: " + result.get(1));
			}
			else
				System.out.println("Pass this to shell: head: Incorrect no of arguments.");
			break;

		case locate:
			if(params.size() == 1)
			{
				Locate locate = new Locate();
				result = locate.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
				{
					result.remove(0);
					System.out.println("Pass this to shell: " + result);					
				}

			}
			else
				System.out.println("Pass this to shell: locate: Incorrect no of arguments.");
			break;

		case ls:
			if(params.size() == 0)
			{
				Ls ls = new Ls();
				result = ls.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: null");
				else
				{
					result.remove(0);
					System.out.println("Pass this to shell: " + result);					
				}
			}
			else
				System.out.println("Pass this to shell: ls: Incorrect no of arguments.");
			break;

		case mkdir:
			if(params.size() == 1)
			{
				Mkdir mkdir = new Mkdir();
				result = mkdir.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: null");	
			}
			else
				System.out.println("Pass this to shell: mkdir: Incorrect no of arguments.");
			break;


		case mv:
			if(params.size() == 2)
			{
				Mv mv = new Mv();
				result = mv.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: null");	
			}
			else
				System.out.println("Pass this to shell: mkdir: Incorrect no of arguments.");
			break;

		case pwd:
			if(params.size() == 0){
				Pwd pwd = new Pwd();
				result = pwd.runCommand(params);
				System.out.println("Pass this to shell: " + result.get(1));
			}
			else			
				System.out.println("Pass this to shell: pwd: Incorrect no of arguments.");
			break;

		case rmdir:
			if(params.size() == 1)
			{
				Rmdir rmdir = new Rmdir();
				result = rmdir.runCommand(params);
				if(result.get(0).equals(ICommand.FAILURE))
					System.out.println("Pass this to shell: " + result.get(1));
				else
					System.out.println("Pass this to shell: null");	
			}
			else
				System.out.println("Pass this to shell: mkdir: Incorrect no of arguments.");
			break;

		case tail:
			Tail tail = new Tail();
			result = tail.runCommand(params);
			System.out.println(result);
			break;

		case touch:
			Touch touch = new Touch();
			result = touch.runCommand(params);
			System.out.println(result);
			break;

		default:
			System.out.println("Command not found.");
		}

	}

	public static void main(String args[]) {

		//new Controller("").call("cat desktop rajat");
	}

}
