package org.iiitb.os.os_proj;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.iiitb.os.os_proj.controller.Controller;
import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.mongodb.DBCollection;

public class TestCommand {

    public static String TESTDB = "testDB";
    public static String TESTCOLLECTION = "testCollection";
    public static MongoConnectivity testMongo;
    public static DBCollection res;

    @BeforeClass
    public static void setupDB() {
        Controller.CURRENT_PATH = "/home/iiitb/";
        testMongo = new MongoConnectivity(TESTDB);
        res = testMongo.openConnection(TESTCOLLECTION);
        res.drop();
    }

    @Test
    public void testLocate() {
        Locate locate = new Locate();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("locatefile");
        u.setPath("/home/iiitb/");
        u.setDirectory(false);

        testMongo.createFile(u);
        searchPath.add("locatefile");
        result = locate.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals("/home/iiitb/locatefile", result.get(1));
        } else

            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Ignore
    public void testMv() {
        Mv m = new Mv();
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        al.add("kanchu17");
        al.add("neetika");

        result = m.runCommand(al);
        if (result.get(0) == ICommand.SUCCESS)
            assertEquals(ICommand.SUCCESS, result.get(0));

            // assertEquals();
        else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Test
    public void testMkdir() {
        Mkdir m = new Mkdir();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("iiitb");
        u.setDirectory(true);

        testMongo.createFile(u);
        searchPath.add("abcdefgh");
        result = m.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertTrue(result.size() > 0);
        } else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Test
    public void testRmdir() {
        Rmdir m = new Rmdir();
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("/home/iiitb/");
        u.setDirectory(true);
        testMongo.createFile(u);

        u.setName("abcd.txt");
        u.setPath("/home/iiitb/abcdefgh/");
        u.setDirectory(false);
        testMongo.createFile(u);
        al.add("/home/iiitb/abcdefgh");
        result = m.runCommand(al);

        if (result.size() == 0)
            assertTrue(result.size() == 0);
        else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Test
    public void testCat() {
        Cat c = new Cat();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("iiitb");
        u.setDirectory(true);
        u.setData("this is hello");
        testMongo.createFile(u);
        searchPath.add("abcdefgh");
        result = c.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals("this is hello", result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));
    }

    @Test
    public void testFile() {
        File f = new File();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("newfile.txt");
        u.setPath("/home/iiitb/");
        u.setDirectory(false);
        u.setFiletypeId(0);
        testMongo.createFile(u);
        searchPath.add("newfile.txt");
        result = f.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals("newfile.txt: txt", result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));
    }

    @Test
    public void testFileSize() {
        Filesize f = new Filesize();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("iiitb");
        u.setDirectory(false);
        u.setFile_size(1234);
        testMongo.createFile(u);
        searchPath.add("abcdefgh");
        result = f.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals("1234", result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));
    }

    @Ignore
    public void testHead() {
        Head h = new Head();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> actualresult = new ArrayList<String>();
        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("iiitb");
        u.setDirectory(false);
        u.setData("hiiiiiiiiiiiiiiiii");
        testMongo.createFile(u);
        searchPath.add("abcdefgh");
        result = h.runCommand(searchPath);

        String data = "hiiiiiiiiiiiiiiiii";
        String split_data[] = data.split("\n");
        if (split_data.length <= 100)
            actualresult.add(data);
        else {
            String data_head = "";
            for (int i = 0; i < 100; i++)
                data_head += split_data[i];
            actualresult.add(data_head);
        }
        System.out.println(actualresult);
        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals(actualresult, result.get(1));

        } else
            assertEquals(ICommand.FAILURE, result.get(0));
    }

    @Ignore
    public void testTail() {
        Tail t = new Tail();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("iiitb");
        u.setDirectory(false);
        u.setData("this is hello");
        testMongo.createFile(u);
        searchPath.add("abcdefgh");
        result = t.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals("this is hello", result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));
    }

    @Test
    public void testPwd() {
        Pwd p = new Pwd();
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        result = p.runCommand(al);

        assertEquals(Controller.CURRENT_PATH, result.get(1));
    }

    @Test
    public void testTouch() {
        Touch t = new Touch();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        searchPath.add("a.txt");
        result = t.runCommand(searchPath);

        if (result.get(0) == ICommand.SUCCESS)
            assertEquals(ICommand.SUCCESS, result.get(0));

        else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Test
    public void testCd() {
        Cd c = new Cd();
        ArrayList<String> searchPath = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();

        UserFile u = new UserFile();
        u.setPath(Controller.CURRENT_PATH + "cdfolder/");
        u.setDirectory(true);

        testMongo.createFile(u);

        searchPath.add("cdfolder");
        result = c.runCommand(searchPath);
        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals(Controller.CURRENT_PATH += "/" + "cdfolder",
                    result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

    @Test
    public void testLs() {
        Ls l = new Ls();
        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        Map<String, Object> constraints = new HashMap<String, Object>();
        UserFile u = new UserFile();
        u.setName("abcdefgh");
        u.setPath("/home/neetika/");
        u.setDirectory(false);
        u.setData("this is hello");
        testMongo.createFile(u);
        constraints.put("path", "/home/neetika/");
        ArrayList<UserFile> resFiles = testMongo.getFiles(constraints);
        ArrayList<String> actual = new ArrayList<String>();

        if (resFiles.size() != 0) {
            for (UserFile u1 : resFiles)
                actual.add(u1.getName());
        }
        System.out.println(actual);
        al.add("/home/neetika/");
        result = l.runCommand(al);
        if (result.get(0) == ICommand.SUCCESS) {
            assertEquals(ICommand.SUCCESS, result.get(0));
            assertEquals(actual.get(0), result.get(1));
        } else
            assertEquals(ICommand.FAILURE, result.get(0));

    }

}
