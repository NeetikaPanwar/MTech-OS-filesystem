package org.iiitb.os.os_proj;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.DBCollection;

import junit.framework.TestCase;

public class TestMongoConnectivity {
	
	public static String TESTDB="testDB";
	public static String TESTCOLLECTION="testCollection";
	public static MongoConnectivity testMongo;
	public static DBCollection res;
	
	
	@BeforeClass
	public static void clearCollectionIfExists(){
		testMongo = new MongoConnectivity();
		res= testMongo.openConnection(TESTCOLLECTION);
    	res.drop();
	}
    
	@Test
	public void testOpenConnection(){
<<<<<<< HEAD
		res= testMongo.openConnection(TESTCOLLECTION);
    	assertNotNull(res);
=======
    	MongoConnectivity testMongo = new MongoConnectivity();
    	//boolean res = testMongo.openConnection();
    	//assertTrue(res);
>>>>>>> branch 'master' of https://github.com/navinpai/OSMT2013.git
    }
	
	@Test
	public void testAddFile(){
		UserFile u= getTestFile();
		testMongo.createFile(u);
		}
	
	public UserFile getTestFile(){
		Date date=new Date();
		UserFile testFile= new UserFile();
		testFile.setData("This is test data");
		testFile.setDate_created(date);
		testFile.setDate_updated(date);
		testFile.setFile_size(1234);
		testFile.setId(1234);
		testFile.setFiletypeId(1);
		testFile.setId(1234);
		testFile.setName("Kanchu17");
		testFile.setPath("/home/kanchan");
		testFile.setTimestamp(date);
		testFile.setUser_created(1);
		testFile.setUser_updated(2);
		return testFile;
	}
	
}
