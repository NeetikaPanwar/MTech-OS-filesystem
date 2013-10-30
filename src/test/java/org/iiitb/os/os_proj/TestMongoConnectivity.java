package org.iiitb.os.os_proj;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;


public class TestMongoConnectivity {

	public static String TESTDB = "testDB";
	public static String TESTCOLLECTION = "testCollection";
	public static MongoConnectivity testMongo;
	public static DBCollection res;

	@BeforeClass
	public static void setupDB(){
		testMongo = new MongoConnectivity(TESTDB);
		res = testMongo.openConnection(TESTCOLLECTION);
		res.drop();
	}
	
	@Before
	public void addSampleFile() {
		 Date date = new Date();
		UserFile testFile = new UserFile();
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
		
		WriteResult result = testMongo.createFile(testFile);
        assertNotNull(result);
		
	}
	
	@After
	public void clearDatabase(){
		BasicDBObject dbo=new BasicDBObject();
		//dbo.put("name","Kanchu17");
		res.findAndRemove(dbo);
	}

	@Test
	public void testOpenConnection() {

		MongoConnectivity testMongo = new MongoConnectivity(TESTDB);
		res = testMongo.openConnection(TESTCOLLECTION);
		assertEquals(TESTCOLLECTION, res.getName());
	}

	@Test
	public void testAddFile() {
		UserFile u = getTestFile();
		WriteResult result = testMongo.createFile(u);
		assertNull(result.getError());
	}
    public UserFile getTestFile() {
		Date date = new Date();
		UserFile testFile = new UserFile();
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
	public void testDeletefile() {
		UserFile u = getTestFile();
		String fname = u.getName();
		/*DBObject result = testMongo.deleteFile(fname);
		String str = (String) result.get("name");
		assertEquals(str, fname);
*/
	}

	@Test
	public void testUpdatefile() {
		UserFile u = getTestFile();

		WriteResult result = testMongo.updateCommon(u);

		assertNull(result.getError());

	}

	@Test
	public void testDisplayFile() {
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "Kanchu17");
		UserFile u = getTestFile();
		String file_name = u.getName();
		ArrayList<UserFile> files = testMongo.getFiles(constraints);
        for (UserFile file : files) {
            assertEquals(file_name,file.getName());
        }
			}
}
