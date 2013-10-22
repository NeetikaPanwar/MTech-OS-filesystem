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
	
	private static final DBCollection NULL = null;
	private static String DATABASE="newDatabase";
	private static String COLLECTION="newCollection";
	
	private BasicDBObject dbObject;
	private MongoClient mongoClient;
	private DB db;
	private DBCollection dbCollection;
	private DBCursor cursor;

	public DBCollection openConnection(String dbcollection) {
		
		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB(MongoConnectivity.DATABASE);
			dbCollection = db.getCollection(dbcollection);

		} catch (UnknownHostException e) {

			success = false;
		}
		return dbCollection;

	}

	/*
	 * public static void main(String[] args) { try {
	 * 
	 * String database = "data"; String collection = "coll"; //
	 * create(database,collection,) // delete(database,collection,"abc");
	 * update(database, collection, "kanchan", "abc");
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */

	public void createFile(UserFile u) {
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
		create(dbObject);
	}

	public void create(BasicDBObject dbObject) {
		DBCollection dbcollection = openConnection(COLLECTION);
		if (dbcollection == NULL) {
			System.out.println("connection failed");
		}
		dbcollection.insert(dbObject);
	}

	public void update(String database, String collection1, String file_name,
			String file_newname) {
		DBCollection dbcollection = openConnection(COLLECTION);
		if (dbcollection == NULL) {
			System.out.println("connection failed");

		}
		System.out.println("collection" + dbcollection);
		BasicDBObject toUpdate = new BasicDBObject();
		toUpdate.put("name", file_newname);
		BasicDBObject toOld = new BasicDBObject();
		toOld.put("name", file_name);

		DBCursor testItemsCursor = dbcollection.find(toOld);

		if (testItemsCursor.hasNext()) {

			DBObject testCodeItem = testItemsCursor.next();

			dbcollection.update(toOld, toUpdate);

		} else {
			System.out.println("record not found");
		}

	}

	public void display(String database, String collection1,
			String file_name) {
		DBCollection dbcollection = openConnection(COLLECTION);
		if (dbcollection == NULL) {
			System.out.println("connection failed");

		}
		System.out.println("database" + db);
		cursor = dbcollection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public void delete(String db1, String collection1, String file_name) {
		DBCollection dbcollection = openConnection(COLLECTION);
		if (dbcollection == NULL) {
			System.out.println("connection failed");

		}
		System.out.println("connection established");
		BasicDBObject deleteQuery = new BasicDBObject();
		deleteQuery.put("name", file_name);
		DBCursor cursor = dbcollection.find(deleteQuery);
		while (cursor.hasNext()) {
			DBObject item = cursor.next();
			System.out.println("file deleted" + item);
			dbcollection.remove(item);
		}

	}
}