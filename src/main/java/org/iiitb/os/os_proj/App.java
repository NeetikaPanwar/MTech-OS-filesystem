package org.iiitb.os.os_proj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.controller.Controller;
import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.iiitb.os.os_proj.shell.Shell;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Shell shell=new Shell();
        shell.setSize(500,500);
		shell.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		shell.setVisible(true);

        boolean fillDatabase=false;

       if(fillDatabase){
            Date date= Calendar.getInstance().getTime();
           User u=new User();
           u.setUserid(1);
           u.setUsername("navin");
           u.setPasswordHash("navin");
           u.setHome("/home/navin");
           u.setRoot(false);

           MongoConnectivity m=new MongoConnectivity(MongoConnectivity.DATABASE);
           m.createUser(u);
//            UserFile u=new UserFile();
//            u.setId(1);
//            u.setDate_created(date);
//            u.setDate_updated(date);
//            u.setPath("/home/navin");
//            u.setName("folder1");
//
//            Controller
      }
    }
}
