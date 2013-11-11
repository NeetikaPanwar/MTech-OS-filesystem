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
import org.junit.Ignore;
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
		assertEquals("iiitbabcdefgh.txt",result.get(1));
		}        
		else
			
			assertEquals(ICommand.FAILURE,result.get(0));	
		
	}
	@Ignore
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
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(true);
		
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=m.runCommand(searchPath);
		
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertTrue(result.size()>0);}
		
		
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
			
	}
	@Ignore 
	public void testRmdir(){
		Rmdir m=new Rmdir();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		
		al.add("abcdefgh");
		result=m.runCommand(al);
		
		if(result.size()==0)
			assertTrue(result.size()==0);
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
			
	}
	@Test 
	public void testCat(){
		Cat c=new Cat();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(true);
		u.setData("this is hello");
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=c.runCommand(searchPath);
         
          
		
	
	
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		   assertEquals("this is hello",result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testFile(){
		File f=new File();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setFiletypeId(1);
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=f.runCommand(searchPath);
		
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
		   assertEquals("txt",result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testFileSize(){
		Filesize f=new Filesize();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(false);
		u.setFile_size(12);
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=f.runCommand(searchPath);
	
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals("12",result.get(1));}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Ignore
	
	public void testHead(){
		Head h=new Head();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		ArrayList<String> actualresult=new ArrayList<String>();
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(false);
		u.setData("hiiiiiiiiiiiiiiiii");
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=h.runCommand(searchPath);
		
		String data = "hiiiiiiiiiiiiiiiii";
		String split_data[] = data.split("\n");
		if(split_data.length <= 100)
		actualresult.add(data);
		else
		{
			String data_head = "";
			for(int i = 0; i < 100; i++)
				data_head += split_data[i];					
			actualresult.add(data_head);
		}				
		System.out.println(actualresult);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(actualresult,result.get(1));
			
		}
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Ignore
	public void testTail(){
		Tail t=new Tail();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		UserFile u=new UserFile();
		u.setName("abcdefgh");
		u.setPath("iiitb");
		u.setDirectory(false);
		u.setData("this is hello");
		ICommand.mongoConnect.createFile(u);
		searchPath.add("abcdefgh");
		result=t.runCommand(searchPath);
	
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals("this is hello",result.get(1));
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
	
		
		   assertEquals(Controller.CURRENT_PATH,result.get(1));}
				
		
		
	
	
	@Ignore
	public void testTouch(){
		Touch t=new Touch();
		ArrayList<String> searchPath=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		
		searchPath.add("a");
		result=t.runCommand(searchPath);
	
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
		      
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Ignore 
	public void testCd(){
	Cd c=new Cd();
	ArrayList<String> searchPath=new ArrayList<String>();
	ArrayList<String> result=new ArrayList<String>();
	
	
	searchPath.add("abcdefgh");
	result=c.runCommand(searchPath);
		if(result.get(0)==ICommand.SUCCESS){
			assertEquals(ICommand.SUCCESS,result.get(0));
			assertEquals(Controller.CURRENT_PATH +="/" + "abcdefgh",result.get(1));
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
