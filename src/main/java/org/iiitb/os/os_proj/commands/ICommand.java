package org.iiitb.os.os_proj.commands;

import com.mongodb.DBCollection;
import org.iiitb.os.os_proj.db.MongoConnectivity;

import java.util.ArrayList;

public interface ICommand {

    DBCollection mongoCollection=new MongoConnectivity().openConnection(MongoConnectivity.DATABASE,MongoConnectivity.COLLECTION);

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
//
//        Pwd
//
//        Long-Double conversion in Mongo
//        Ls regex
//        Login Logout
