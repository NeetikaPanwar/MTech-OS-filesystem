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
import org.junit.Test;

public class TestCommand{

	@Test
	public void testLocate(){
		Locate locate=new Locate();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(true);
		
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=locate.runCommand(searchPath);
		
		if(result.get(0)==ICommand.SUCCESS){
		assertEquals(ICommand.SUCCESS,result.get(0));
		assertEquals(u.getPath(),result.get(1));
		}        
		else
			
			assertEquals(ICommand.FAILURE,result.get(0));	
		
	}
	@Test
	public void testMv(){
		Mv m=new Mv();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		al.add("neetika");
	
		result=m.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
		
		   //assertEquals();
			else
			assertEquals(ICommand.FAILURE,result.get(0));	
			
		
	}
	@Test 
	public void testMkdir(){
		Mkdir m=new Mkdir();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		al.add("neetika");
		result=m.runCommand(al);
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "neetika");
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("isDirectory", "true");
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertTrue(receivedFile.size()>0);}
		
		
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
			
	}
	@Test 
	public void testRmdir(){
		Rmdir m=new Rmdir();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=m.runCommand(al);
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "kanchu17");
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("isDirectory", "true");
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertTrue(receivedFile.size()==0);}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
			
	}
	@Test 
	public void testCat(){
		Cat c=new Cat();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "navin");
	constraints.put("path", "/home/kanchan");
		constraints.put("isDirectory", "false");
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		System.out.println(receivedFile.get(0));
          String expecteddata =  receivedFile.get(0).getData();
          System.out.println(expecteddata);
		
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
	
		al.add("navin");
		
		result=c.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		   assertEquals(expecteddata,result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testFile(){
		File f=new File();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "kanchu17");
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("isDirectory", "false");
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		
		String s=String.valueOf(receivedFile.get(0).getFiletypeId());
		al.add("kanchu17");
		result=f.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		   assertEquals(s,result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testFileSize(){
		Filesize f=new Filesize();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", "kanchu17");
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("isDirectory", "false");
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		
		String s=String.valueOf(receivedFile.get(0).getFile_size());
		al.add("kanchu17");
		result=f.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(s,result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testHead(){
		Head h=new Head();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("name", "kanchu17");
		constraints.put("isDirectory", "false");
		String expecteddata = null;
		ArrayList<UserFile> resFiles = ICommand.mongoConnect.getFiles(constraints);
		if(resFiles != null)	//File exists... display data
		{
			String data = resFiles.get(0).getData();
			String split_data[] = data.split("\n");
			if(split_data.length <= 100)
				expecteddata=data;
			else
			{
				String data_head = "";
				for(int i = 0; i < 100; i++)
					data_head += split_data[i];
					
				expecteddata=data_head;
			}
			
		}
		
		al.add("kanchu17");
		result=h.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(expecteddata,result.get(1));
			
		}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testTail(){
		Tail t=new Tail();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		//Search if file exists
				Map<String, String> constraints = new HashMap<String, String>();
				constraints.put("path", Controller.CURRENT_PATH);
				constraints.put("name", "kanchu17");
				constraints.put("isDirectory", "false");
				ArrayList<UserFile> resFiles = ICommand.mongoConnect.getFiles(constraints);
String expecteddata=null;
				if(resFiles != null)	//File exists... display data
				{
					String data = resFiles.get(0).getData();
					String split_data[] = data.split("\n");
					if(split_data.length <= 100)
						expecteddata=data;
					else
					{
						String data_head = "";
						for(int i = (split_data.length - 100); i < split_data.length; i++)
							data_head += split_data[i];
							
						expecteddata=data_head;
					}
				}
		al.add("kanchu17");
		result=t.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(expecteddata,result.get(1));
			}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testPwd(){
		Pwd p=new Pwd();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		result=p.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		   assertEquals(Controller.CURRENT_PATH,result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testTouch(){
		Touch t=new Touch();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<UserFile> receivedFile = new ArrayList<UserFile>();
		Map<String, String> constraints = new HashMap<String, String>();
		
		al.add("kanchu17");
		result=t.runCommand(al);
		constraints.put("name", "kanchu17");
		constraints.put("path", Controller.CURRENT_PATH);
		receivedFile = ICommand.mongoConnect.getFiles(constraints);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		      assertTrue(receivedFile.size()>0);}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testCd(){
	Cd c=new Cd();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", Controller.CURRENT_PATH);
		constraints.put("name", "kanchu17");;
		constraints.put("isDirectory", "true");
		ArrayList<UserFile> resFiles = ICommand.mongoConnect.getFiles(constraints);
		al.add("kanchu17");
		result=c.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(Controller.CURRENT_PATH += "/" + resFiles.get(0).getPath(),result.get(1));
		}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testLs(){
		Ls l=new Ls();
		ArrayList<String> al = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("path", Controller.CURRENT_PATH);
		ArrayList<UserFile> resFiles = ICommand.mongoConnect.getFiles(constraints);
		ArrayList<String> actual = new ArrayList<String>();
		
		while(resFiles != null){
			for(UserFile u: resFiles)
				actual.add(u.getName());
		}
		result=l.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(actual,result);
		}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
		
	}
	
	
	
	
	
	
	
	
}
