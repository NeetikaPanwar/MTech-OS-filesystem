package org.iiitb.os.os_proj.commands;

import com.mongodb.DBCollection;
import org.iiitb.os.os_proj.db.MongoConnectivity;

import java.util.ArrayList;

public interface ICommand {

    MongoConnectivity mongoCollection=new MongoConnectivity(MongoConnectivity.DATABASE);
    public static String SUCCESS="SUCCESS";
    public static String FAILURE="FAILURE";

	public ArrayList<String> runCommand(ArrayList<String> params);
}

//
//File FileSize Cat -> Ankit
//Cd Touch
//        Head
//        Tail
//        -Rajat
//Ls -?
//        Locate
//        Mkdir
//        Rmdir
//        Mv
//
//        Login Logout
