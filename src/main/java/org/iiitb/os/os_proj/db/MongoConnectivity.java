package org.iiitb.os.os_proj.db;

import java.net.UnknownHostException;

import org.iiitb.os.os_proj.UserFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class MongoConnectivity {
	

	static 	BasicDBObject file;;
	static 	MongoClient mongoClient;
	static DB db ;
	static DBCollection collection ;
	static DBCursor cursor;
	
	
		public static boolean openConnection()
		{
			boolean success = true;
		try {
			mongoClient= new MongoClient("localhost", 27017);
			
		} catch (UnknownHostException e) {
			
			success = false;
		}
		return success;
		
	}

	
	
	
		
	
	public static void main(String[] args) {
		try {
			
			
			
			String database="data";
			String collection="coll";
			//create(database,collection,)
			//delete(database,collection,"abc");
			update(database,collection,"kanchan","abc");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void create(BasicDBObject a){
		//insert into db
	}
	
	public void createFile(UserFile u){
		
		create(file);
	}
	public static void create(String newDatabase, String newCollection, UserFile f)
	{
		UserFile user_file = f;
		boolean b = openConnection();
		if(b==false)
		{
			System.out.println("connection failed");
		}
		db = mongoClient.getDB("newDatabase");
		collection = db.getCollection("newCollection");
		file = new BasicDBObject();
		file.put("id", user_file.getId());
		file.put("name", user_file.getName());
		file.put("filetypeId", user_file.getFiletypeId() );
		file.put("timestamp", user_file.getTimestamp());
		file.put("date_created", user_file.getDate_created());
		file.put("date_updated", user_file.getDate_updated());
		file.put("user_created", user_file.getUser_created());
		file.put("user_updated", user_file.getUser_updated());
		file.put("path", user_file.getPath());
		file.put("file_size", user_file.getFile_size());
		file.put("data", user_file.getData());
		collection.insert(file);
	}
	
	public static void update(String database,String collection1, String file_name,String file_newname)
	{
		boolean b = openConnection();
		if(b==false)
		{
			System.out.println("connection failed");

}
		db = mongoClient.getDB(database);
		collection = db.getCollection(collection1);
		System.out.println("database"+db);
		System.out.println("collection"+collection);
		BasicDBObject toUpdate = new BasicDBObject();
		toUpdate.put("name", file_newname);
		BasicDBObject toOld = new BasicDBObject();
		toOld.put("name", file_name);
		
		DBCursor testItemsCursor = collection.find(toOld);
		
		if(testItemsCursor.hasNext()) {
			
		DBObject testCodeItem = testItemsCursor.next();
		
		collection.update(toOld,toUpdate);
		
		}
		else 
		{
			System.out.println("record not found");
		}
	     
		}
		

	
	public static void display(String database,String collection1,String file_name)
	{
		boolean b = openConnection();
		if(b==false)
		{
			System.out.println("connection failed");

}
		db = mongoClient.getDB("db1");
		collection = db.getCollection("Collection1");
		System.out.println("database"+db);
		cursor = collection.find();
		
		while(cursor.hasNext())
		{
			System.out.println(cursor.next());
		}
}
	public static void delete(String db1,String collection1, String file_name)
	{
		boolean b = openConnection();
		if(b==false)
		{
			System.out.println("connection failed");

}
		System.out.println("connection established");
		db = mongoClient.getDB(db1);
		System.out.println("database"+db);
		collection = db.getCollection(collection1);
		System.out.println("collection"+collection);
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("name", file_name);
		DBCursor cursor = collection.find(deleteQuery);
		while (cursor.hasNext()) {
		DBObject item = cursor.next();
		System.out.println("file deleted"+item);
		collection.remove(item);
		}
		
}
}