package org.iiitb.os.os_proj;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.iiitb.os.os_proj.commands.Cat;
import org.iiitb.os.os_proj.commands.Cd;
import org.iiitb.os.os_proj.commands.File;
import org.iiitb.os.os_proj.commands.Filesize;
import org.iiitb.os.os_proj.commands.Head;
import org.iiitb.os.os_proj.commands.ICommand;
import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.commands.Mkdir;
import org.iiitb.os.os_proj.commands.Mv;
import org.iiitb.os.os_proj.commands.Pwd;
import org.iiitb.os.os_proj.commands.Rmdir;
import org.iiitb.os.os_proj.commands.Tail;
import org.iiitb.os.os_proj.commands.Touch;
import org.junit.Test;

public class TestCommand {

	@Test
	public void testLocate(){
		Locate l=new Locate();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=l.runCommand(al);
		
		if(result.get(0)==ICommand.SUCCESS){
		assertEquals(ICommand.SUCCESS,result.get(0));
		assertEquals("/kanchan/kanchu17/",result.get(1));}        
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
			else
			
				assertEquals(ICommand.FAILURE,result.get(0));	
			
		
	}
	@Test 
	public void testMkdir(){
		Mkdir m=new Mkdir();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=m.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
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
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
			
	}
	@Test 
	public void testCat(){
		Cat c=new Cat();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=c.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testFile(){
		File f=new File();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=f.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testFileSize(){
		Filesize f=new Filesize();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=f.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testHead(){
		Head h=new Head();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=h.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testTail(){
		Tail t=new Tail();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=t.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	@Test 
	public void testPwd(){
		Pwd p=new Pwd();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		
		result=p.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testTouch(){
		Touch t=new Touch();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=t.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	@Test 
	public void testCd(){
	Cd c=new Cd();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=c.runCommand(al);
		if(result.get(0)==ICommand.SUCCESS)
			assertEquals(ICommand.SUCCESS,result.get(0));
			else
				assertEquals(ICommand.FAILURE,result.get(0));	
		
		
	}
	
	
	
	
	
	
	
}
