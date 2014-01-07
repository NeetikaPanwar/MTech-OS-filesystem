package org.iiitb.os.os_proj.utils;

public class GetFileType {
	
	public static String fileExt[] =  { "txt", "jpg", "png", "mp3", "wav"};
	
	public static String getFileExt(int id){
		
		return fileExt[id-1];
	}
	
	public static int getFileExtId(String ext){
		int id = 0;
		for(int i=0; i<fileExt.length; i++)
		{
			if(fileExt[i].equals(ext))
				id = i + 1;
		}
		return id;
	}

}
