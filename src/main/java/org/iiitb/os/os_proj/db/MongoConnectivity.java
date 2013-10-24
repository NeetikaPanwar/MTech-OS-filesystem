package org.iiitb.os.os_proj.db;

import java.net.UnknownHostException;
import java.util.Date;

import org.bson.types.ObjectId;
import org.iiitb.os.os_proj.UserFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoConnectivity {
	
	private static final DBCollection NULL = null;
	private static String DATABASE="newDatabase";
	private static String COLLECTION="newCollection";
	
	private BasicDBObject dbObject;
	private BasicDBObject dbObject_update;
	private MongoClient mongoClient;
	private DB db;
	private DBCollection dbCollection;
	private DBCollection dbcollection;
	private DBCursor cursor;

	public DBCollection openConnection(String dbcollection) {
		
		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB(MongoConnectivity.DATABASE);
			dbCollection = db.getCollection(dbcollection);

		} catch (UnknownHostException e) {

			dbCollection = null;
		}
		return dbCollection;

	}

	
	/* public static void main(String[] args) { 
		  try {
	   MongoConnectivity mainObject = new MongoConnectivity();
	  
	   //deleteFile.deleteFile("abc");
	  //UserFile u = mainObject.getTestFile();
	 //    mainObject.updateCommon(u);
	     mainObject.displayFile("file1");
	 //  deleteFile.update("user", "nitika", "neha");
	  } catch (Exception e) { 
	  e.printStackTrace(); } }
	 */

	public WriteResult createFile(UserFile u) {
		dbObject = new BasicDBObject();
		dbObject.put("id", u.getId());
		dbObject.put("name", u.getName());
		dbObject.put("filetypeId", u.getFiletypeId());
		dbObject.put("timestamp", u.getTimestamp());
		dbObject.put("date_created", u.getDate_created());
		dbObject.put("date_updated", u.getDate_updated());
		dbObject.put("user_created", u.getUser_created());
		dbObject.put("user_updated", u.getUser_updated());
		dbObject.put("path", u.getPath());
		dbObject.put("file_size", u.getFile_size());
		dbObject.put("data", u.getData());
		return create(dbObject);
	}

	public WriteResult create(BasicDBObject dbObject) {
		 dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		}
		WriteResult result=dbcollection.insert(dbObject);
		System.out.println(result);
		return result;
	}

	public WriteResult updateCommon(UserFile user_file_info) {
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");

		}
		BasicDBObject updated_object = updateFile(user_file_info);
		BasicDBObject curr_object = new BasicDBObject();
		curr_object.put("id", user_file_info.getId());
		
		DBCursor cursor = dbcollection.find(curr_object);
		DBObject current_object = new BasicDBObject();
		while(cursor.hasNext())
		{
			current_object = cursor.next();
		}
		
		return dbcollection.update(current_object, updated_object);
	}
	public BasicDBObject updateFile(UserFile user_file)
	{
		dbObject = new BasicDBObject();
		dbObject.put("id", user_file.getId());
		dbObject.put("name", user_file.getName());
		dbObject.put("filetypeId", user_file.getFiletypeId());
		dbObject.put("timestamp", user_file.getTimestamp());
		dbObject.put("date_created", user_file.getDate_created());
		dbObject.put("date_updated", user_file.getDate_updated());
		dbObject.put("user_created", user_file.getUser_created());
		dbObject.put("user_updated", user_file.getUser_updated());
		dbObject.put("path", user_file.getPath());
		dbObject.put("file_size", user_file.getFile_size());
		dbObject.put("data", user_file.getData());
		return dbObject;
	}
	//display all documents/files
	public void display() {
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");

		}
		System.out.println("database" + db);
		cursor = dbcollection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	//display a particular file 
	public DBCursor displayFile(String file_name)
	{
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");

		}
		BasicDBObject searchFile = new BasicDBObject();
		DBObject fileToDisplay = new BasicDBObject();
		searchFile.put("name", file_name);
		DBCursor cursor = dbcollection.find(searchFile);
		while(cursor.hasNext())
		{
			fileToDisplay = cursor.next();
			System.out.println(fileToDisplay);
		}
		return cursor;
	}

	public  DBObject deleteFile(String file_name) {
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		}
		
		BasicDBObject basicObject = new BasicDBObject();
		DBObject basicObject1 = new BasicDBObject(); 
		basicObject.put("name", file_name);
		DBCursor  cursor = dbcollection.find(basicObject); 
		while(cursor.hasNext())
		{
			
		basicObject1 = cursor.next();
		basicObject1.get("_id");
		}
		
		return basicObject1;
		/*while (cursor.hasNext()) {
			ObjectId item = (ObjectId) cursor.next().get("_id");
			System.out.println("file deleted" + item.toString());
			//dbcollection.remove(basicObject);
		}*/
	}
	public void delete(DBObject objectToDelete)
	{
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		}
		DBObject dbo= dbcollection.findAndRemove(objectToDelete);
	}
	
}