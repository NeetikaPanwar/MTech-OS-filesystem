package org.iiitb.os.os_proj.commands;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import java.util.ArrayList;

public class Ls implements ICommand {

    public ArrayList<String> runCommand(ArrayList<String> params) {
        ArrayList<String> result = new ArrayList<String>();
        BasicDBObject searchObject = new BasicDBObject();
        searchObject.put("path", params.get(0));
        DBCursor cursor = mongoCollection.find(searchObject);
        if (cursor != null) {
            result.add("SUCCESS");
        } else {
            result.add("FAILURE");
        }
        while (cursor != null ? cursor.hasNext() : false) {
            result.add((String) cursor.next().get("name"));
        }
        return result;
    }

}

