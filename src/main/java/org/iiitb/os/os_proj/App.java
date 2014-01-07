package org.iiitb.os.os_proj;

import java.util.Calendar;
import java.util.Date;

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
           if(fillDatabase){
               Date date= Calendar.getInstance().getTime();
               MongoConnectivity m=new MongoConnectivity(MongoConnectivity.DATABASE);
               User u=new User();

               u.setUserid(0);
               u.setUsername("root");
               u.setPasswordHash("root");
               u.setHome("/home/root/");
               u.setRoot(true);
               m.createUser(u);

               u.setUserid(1);
               u.setUsername("navin");
               u.setPasswordHash("navin");
               u.setHome("/home/navin/");
               u.setRoot(false);
               m.createUser(u);

               u.setUserid(2);
               u.setUsername("neetika");
               u.setPasswordHash("neetika");
               u.setHome("/home/neetika/");
               u.setRoot(false);
               m.createUser(u);

               u.setUserid(3);
               u.setUsername("rajat");
               u.setPasswordHash("rajat");
               u.setHome("/home/rajat/");
               u.setRoot(false);
               m.createUser(u);

               u.setUserid(4);
               u.setUsername("kanchan");
               u.setPasswordHash("kanchan");
               u.setHome("/home/kanchan/");
               u.setRoot(false);
               m.createUser(u);

               u.setUserid(5);
               u.setUsername("ankit");
               u.setPasswordHash("ankit");
               u.setHome("/home/ankit/");
               u.setRoot(false);
               m.createUser(u);

               UserFile u2=new UserFile();
               u2.setId(1);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/system/rootFolder1/");
               u2.setDirectory(true);
               u2.setName("rootFolder1");
               u2.setOwner(0);
               m.createFile(u2);

               u2.setId(2);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/system/rootFolder2/");
               u2.setDirectory(true);
               u2.setName("rootFolder2");
               u2.setOwner(0);
               m.createFile(u2);

               u2.setId(11);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/system/rootFolder1/fileInFolder1/");
               u2.setDirectory(false);
               u2.setData("This is a file in root folder 1");
               u2.setName("fileInFolder1");
               u2.setFile_size(1234);
               u2.setFiletypeId(1);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(0);
               m.createFile(u2);


               u2.setId(12);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/system/rootFolder2/fileInFolder2/");
               u2.setDirectory(false);
               u2.setData("This is a file in root folder 2");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(2);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(0);
               m.createFile(u2);

               u2.setId(111);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/navin/");
               u2.setDirectory(false);
               u2.setData("Only Navin can access this file");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(1);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(1);
               m.createFile(u2);


               u2.setId(222);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/neetika/");
               u2.setDirectory(false);
               u2.setData("Only Neetika can access this file");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(2);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(2);
               m.createFile(u2);

               u2.setId(333);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/rajat/");
               u2.setDirectory(false);
               u2.setData("Only Rajat can access this file");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(3);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(3);
               m.createFile(u2);

               u2.setId(444);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/Kanchan/");
               u2.setDirectory(false);
               u2.setData("Only Kanchan can access this file");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(4);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(4);
               m.createFile(u2);

               u2.setId(555);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/ankit/");
               u2.setDirectory(false);
               u2.setData("Only Ankit can access this file");
               u2.setName("fileInFolder2");
               u2.setFile_size(1234);
               u2.setFiletypeId(2);
               u2.setTimestamp(date);
               u2.setUser_created(1);
               u2.setUser_updated(2);
               u2.setOwner(5);
               m.createFile(u2);
      }
       }
    }
}
