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
    private boolean validCommand=false;

    public Controller(String path, String user) {
        Controller.CURRENT_PATH = path;
        Controller.CURRENT_USER = user;
    }

    public String call(String cmd) {
        String returnString="";

        cmd = cmd.trim();
        List<String> params = new LinkedList<String>(Arrays.asList(cmd
                .split(" ")));
        if(params.get(0).equals("sudo")){
            returnString="[sudo] password for " + Controller.CURRENT_USER;
            String password = ""; //password received from shell
            while(!password.equals("password of current_user"))
            {
                returnString="Sorry, try again\n [sudo] password for " + Controller.CURRENT_USER;
            }

            params.remove(0);//remove sudo
        }
        String split_cmd = params.remove(0);

        // ArrayList<String> params = ;
        ArrayList<String> result = new ArrayList<String>();

        System.out.println(split_cmd+" in this");
        for(commands c:commands.values()){
            if (c.name().equals(split_cmd)) {
                validCommand=true;
            }
        }

        if(validCommand){


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
                        System.out.println("First");
                        result = ls.runCommand(params);
                        if(result.get(0).equals(ICommand.SUCCESS))
                        {
                            System.out.println("Second");
                            result.remove(0);
                            for(String i:result)
                            {
                                returnString+="\n"+i;
                            }


                            System.out.println(returnString+ "in ls");
                        }
                    }
                    else
                        returnString="ls: Incorrect no of arguments.";
                    break;

                case mkdir:
                    if(params.size() == 1)
                    {
                        Mkdir mkdir = new Mkdir();
                        result = mkdir.runCommand(params);
                        if(result.get(0).equals(ICommand.FAILURE))
                            returnString=result.get(1);
                  //ELSE SUCCESS
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
                        returnString=result.get(1);
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
                        System.out.println("Pass this to shell: rmdir: Incorrect no of arguments.");
                    break;

                case tail:
                    if(params.size() == 1)
                    {
                        Tail tail = new Tail();
                        result = tail.runCommand(params);
                        if(result.get(0).equals(ICommand.FAILURE))
                            System.out.println("Pass this to shell: " + result.get(1));
                        else
                            System.out.println("Pass this to shell: " + result.get(1));
                    }
                    else
                        System.out.println("Pass this to shell: tail: Incorrect no of arguments.");
                    break;

                case touch:
                    Touch touch = new Touch();
                    result = touch.runCommand(params);
                    if(result.get(0).equals(ICommand.FAILURE))
                        returnString=result.get(0);
                    break;

                default:
                    System.out.println(cmd + ": Command not found.");
            }

        }
        else{
            returnString="Invalid Command";
        }

        return returnString;

    }

}
