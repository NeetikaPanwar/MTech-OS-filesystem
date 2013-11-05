package org.iiitb.os.os_proj;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.iiitb.os.os_proj.commands.ICommand;
import org.iiitb.os.os_proj.commands.Locate;
import org.iiitb.os.os_proj.commands.Mkdir;
import org.iiitb.os.os_proj.commands.Mv;
import org.iiitb.os.os_proj.commands.Rmdir;
import org.junit.Test;

public class TestCommand {

	@Test
	public void testLocate(){
		Locate l=new Locate();
		ArrayList<String> al=new ArrayList<String>();
		ArrayList<String> result=new ArrayList<String>();
		al.add("kanchu17");
		result=l.runCommand(al);
		//assertEquals("/kanchan/kanchu17/",result.get(1));
		if(result.get(0)==ICommand.SUCCESS)
		assertEquals(ICommand.SUCCESS,result.get(0));
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
	
}
