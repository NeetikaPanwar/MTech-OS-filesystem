package org.iiitb.os.os_proj;

import org.iiitb.os.os_proj.db.MongoConnectivity;
import org.junit.Test;

import junit.framework.TestCase;

public class TestMongoConnectivity extends TestCase {
    @Test
	public void testOpenConnection(){
    	MongoConnectivity testMongo = new MongoConnectivity();
    	boolean res = testMongo.openConnection();
    	assertTrue(res);
    }
}
