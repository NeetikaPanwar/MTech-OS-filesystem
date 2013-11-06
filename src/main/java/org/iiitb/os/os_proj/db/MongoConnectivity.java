package org.iiitb.os.os_proj.db;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.iiitb.os.os_proj.User;
import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.commands.ICommand;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class MongoConnectivity{
	public static String DATABASE="newDatabase";
	public static String COLLECTION="newCollection";

	private BasicDBObject dbObject;
	private MongoClient mongoClient;
	private DB db;
	private DBCollection dbCollection;
	private DBCollection dbcollection;
	private ArrayList<DBObject> listOfObjects = new ArrayList<DBObject>();

	public MongoConnectivity(String database){
		DATABASE=database;
	}

	public DBCollection openConnection(String collection) {
		COLLECTION=collection;

		try {
			mongoClient = new MongoClient("localhost", 27017);
			db = mongoClient.getDB(DATABASE);
			dbCollection = db.getCollection(COLLECTION);

		} catch (UnknownHostException e) {

			dbCollection = null;
		}
		return dbCollection;

	}


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
		dbObject.put("isDirectory",u.isDirectory());
		return create(dbObject);
	}

	public WriteResult createUser(User u) {
		dbObject = new BasicDBObject();

		dbObject.put("userid", u.getUserid());
		dbObject.put("username", u.getUsername());
		dbObject.put("passwordhash", u.getPasswordHash());
		dbObject.put("salt", u.getSalt());
		dbObject.put("home", u.getHome());
		dbObject.put("isroot", u.getRoot());
		return create(dbObject);
	}

	private WriteResult create(BasicDBObject dbObject) {
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		}
		WriteResult result = null;
		if (dbcollection != null) {
			result = dbcollection.insert(dbObject);
		}
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
		while (cursor.hasNext()) {
			current_object = cursor.next();
		}

		return dbcollection.update(current_object, updated_object);
	}

	public BasicDBObject updateFile(UserFile user_file) {
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
		dbObject.put("isDirectory", user_file.isDirectory());
		return dbObject;
	}
	    public static void main(String args[])
	    {
	    	
	    	UserFile u = ICommand.mongoConnect.getTestFile();
	    	ICommand.mongoConnect.createFile(u);
	    	
	    	//mongoConnect.updateCommon(u);
	    }
	public UserFile getTestFile() {
		Date date = new Date();
		UserFile testFile = new UserFile();
		testFile.setData("This is test data today");
		testFile.setDate_created(date);
		testFile.setDate_updated(date);
		testFile.setFile_size(1234);
		testFile.setId(1234);
		testFile.setFiletypeId(1);
		testFile.setId(1234);
		testFile.setName("ruchita");
		testFile.setPath("/home/kanchan");
		testFile.setTimestamp(date);
		testFile.setUser_created(1);
		testFile.setUser_updated(2);
		testFile.setDirectory(false);
		return testFile;
	}

	//display a particular file
	public ArrayList<UserFile> getFiles(Map<String, String> constraints) {
		ArrayList<UserFile> files = new ArrayList<UserFile>();
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");

		}
		BasicDBObject searchFile = new BasicDBObject();
		for (Entry<String, String> entry : constraints.entrySet()) {
			searchFile.put(entry.getKey(), entry.getValue());
		}

		DBCursor cursor = dbcollection.find(searchFile);
		System.out.println();
		while (cursor.hasNext()) {
			files.add(convertToUserFile(cursor.next()));
		}
		for (UserFile file : files) {
			System.out.println(file.getName());
		}
		return files;
	}

	//Get List of Users
	public ArrayList<User> getUsers(Map<String, String> constraints) {
		ArrayList<User> files = new ArrayList<User>();
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");

		}
		BasicDBObject searchUser = new BasicDBObject();
		for (Entry<String, String> entry : constraints.entrySet()) {
			searchUser.put(entry.getKey(), entry.getValue());
		}

		DBCursor cursor = dbcollection.find(searchUser);
		while (cursor.hasNext()) {
			files.add(convertToUser(cursor.next()));
		}
		for (User file : files) {
			System.out.println(file.getUsername());
		}
		return files;
	}

	private User convertToUser(DBObject dbo) {
		User u = new User();
		u.setUserid(((Integer) dbo.get("userid")));
		u.setUsername((String) dbo.get("username"));
		u.setPasswordHash((String) dbo.get("passwordhash"));
		u.setSalt((String)dbo.get("salt"));
		u.setHome((String)dbo.get("home"));
		u.setRoot((Boolean) dbo.get("isroot"));
		return u;
	}

	private UserFile convertToUserFile(DBObject dbo) {
		UserFile u = new UserFile();
		u.setId(((Number) dbo.get("id")).longValue());
		u.setName((String) dbo.get("name"));
		u.setFiletypeId((Integer) dbo.get("filetypeId"));
		u.setTimestamp((Date) dbo.get("timestamp"));
		u.setDate_created((Date) dbo.get("date_created"));
		u.setDate_updated((Date) dbo.get("date_updated"));
		u.setUser_created((Integer) dbo.get("user_created"));
		u.setUser_updated((Integer) dbo.get("user_updated"));
		u.setPath((String) dbo.get("path"));
		u.setFile_size(((Number) dbo.get("file_size")).longValue());
		u.setData((String) dbo.get("data"));
		u.setDirectory((Boolean)dbo.get("isDirectory"));

		return u;
	}


	public void deleteFile(String file_name) {
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		}

		BasicDBObject basicObject = new BasicDBObject();
		basicObject.put("name", file_name);
		DBCursor cursor = dbcollection.find(basicObject);
		while (cursor.hasNext()) {

			listOfObjects.add(cursor.next());

		}
		delete(listOfObjects);

		/*while (cursor.hasNext()) {
			ObjectId item = (ObjectId) cursor.next().get("_id");
			System.out.println("file deleted" + item.toString());
			//dbcollection.remove(basicObject);
		}*/
	}

	private void delete(ArrayList<DBObject> listOfObjects2) {
		int i = 0;
		dbcollection = openConnection(COLLECTION);
		if (dbcollection == null) {
			System.out.println("connection failed");
		} 
		else 
		{
			while (i < listOfObjects.size()) {
				dbcollection.findAndRemove(listOfObjects2.get(i));
				i++;
			}
		}

	}

}
