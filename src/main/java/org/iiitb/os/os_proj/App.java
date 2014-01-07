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
               
               u2.setId(1265);
               u2.setDate_created(date);
               u2.setDate_updated(date);
               u2.setPath("/home/navin/");
               u2.setDirectory(false);
               u2.setData("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maODZc2ovkpaNTxI/6ycYZezN3ua5KZPlI1LJylry7I/SHb8cOnzoL/0vM9XAu93bPZj9n3IXw6896rUFhh/TSq3LZILWdPkwtsvy4XVitKOf2xoiDbkXQEEQADkfyIS389f4oPVNauXj6x7dbBcKkg19qDSntjtA/fb3alKu/75Uvk85xX52U/bKntFdsftbSVfG4yXdxl/TfR/tkNovxZFVWtrcnJSnFi8OgMIgABIcza3r0k+mvG44QFpri7vSpGCzCkA0pLSPqeTzw8EEAABEAABEAABEAIQAAEQAAEQAAEQACEAARAAARAAARAAARAAARAAARAAIQABEAABEAABEAAhAAEQAAEQAAEQAAEQAhAAARAAARAAARAAARAAARAAARACEAABEAABEAABEAABEAABEAABEAABEAAhAAEQAAEQAAEQAAEQAAEQAAEQACEAARAAARAAARAAARAAARAAARAAARAAARACEAABEAABEAABEAABEAABEAABEAIQag5ATFKQPEbqPs/Qh72jq/ssQwpXTZbZQX76ADZyMwO9Zd2rgw0PSGVekj5wr+xJ10pTVsO+DNn3znTlgHyW84o07H9L6bU0R3Va1bvTpGp3qvZXTTXampRPiJGizr8Ui0d7JRW5/lIOh/YGkNYAiH1n8M74MPlk/rOyJXGogp6RLQlDZXO88ds45ymxrHlRqvOTDf8kumb2s/LKiDCZMTpCWTOfi5Tn4npJ29vaKAPktjZtZExcoH4uldeiute0EsdEyqGXHpPTU4bIKVW9/Jicmzd161dLU2ZfSFuSoKSUxfMr3sue1FRf3xZAnBwQe/H9AtQ9tffylnef7y+XtacrFTuc5q5mZ7L+BG9kPOwARgTeo2yo04+rQ5ufyLauHaXEy1Usiipy/ZV8/faKqUafi+DQggBR/bvBHydGOMVTu7MEIC0UEG2nts2ni9gU/TZhz+LZQS5mrgIQAAEQAhAAARACEAIQAAEQAAEQAAEQAhAAARAAARACEAABEAAhAAEQAAEQACEAARAAIQAhACEAARAAARAAIQABEAABEAAhAAEQAAEQAhAAARAAARACEAABEAABEAIQAhAAARAAARACEAABEAABEAIQAAEQACEAARAAARAAIQABEAABEAAhACEAARAAARAAIQABEAABEAAhAAEQAAEQAhAAARC6CcjKMTFF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");
               u2.setName("Mario.png");
               u2.setFile_size(1234);
               u2.setFiletypeId(3);
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
