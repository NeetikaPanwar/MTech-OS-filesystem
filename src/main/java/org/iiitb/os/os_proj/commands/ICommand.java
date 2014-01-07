package org.iiitb.os.os_proj.commands;

import org.iiitb.os.os_proj.db.MongoConnectivity;


import java.util.ArrayList;
import java.util.List;

public interface ICommand {

	public static MongoConnectivity mongoConnect = new MongoConnectivity(MongoConnectivity.DATABASE);
    public static String SUCCESS="SUCCESS";
    public static String FAILURE="FAILURE";

	public ArrayList<String> runCommand(List<String> params);
}
