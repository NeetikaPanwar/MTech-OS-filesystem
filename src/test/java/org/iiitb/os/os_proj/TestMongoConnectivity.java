package org.iiitb.os.os_proj;

import static org.junit.Assert.*;


import java.util.Date;

import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

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

    	MongoConnectivity testMongo = new MongoConnectivity();
    	 res = testMongo.openConnection(TESTCOLLECTION);
    	assertEquals(TESTCOLLECTION,res);
    }
	
	@Test
	public void testAddFile(){
		UserFile u= getTestFile();
		WriteResult result=testMongo.createFile(u);
		assertNull(result.getError());
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
	@Test
	public void testDeletefile(){
		UserFile u= getTestFile();
		String fname=u.getName();
		DBObject result= testMongo.deleteFile(fname);
		String str=(String) result.get("name");
		System.out.println();                                                                                                                                                              
		assertEquals(str,fname);
		
	}
	
		@Test
		public void testUpdatefile(){
			UserFile u= getTestFile();
			
			WriteResult result= testMongo.updateCommon(u);
			
			assertNull(result.getError());
			
			
		}
		@Test
		public void testDisplayFile(){
			UserFile u=getTestFile();
			String file_name=u.getName();
			DBCursor  cursor= testMongo.displayFile(file_name);
			BasicDBObject basicObject1 = (BasicDBObject) cursor.next();
			String str=(String) basicObject1.get("name");
			
			//assertTrue(str.equals(file_name));
			assertEquals(str,file_name);
			
		}
	}
	


