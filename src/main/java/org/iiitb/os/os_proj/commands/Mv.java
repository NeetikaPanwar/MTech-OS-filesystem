package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.controller.Controller;
import org.iiitb.os.os_proj.db.MongoConnectivity;

import com.mongodb.WriteResult;

public class Mv implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result=new ArrayList<String>();
		//get 1st and 2nd parameter
		int i=0;
		String source=params.get(0);
		String destination=params.get(1);
		MongoConnectivity testMongo=new MongoConnectivity(MongoConnectivity.DATABASE);
		Map<String, String> constraints = new HashMap<String, String>();
		constraints.put("name", source);
		constraints.put("path",Controller.CURRENT_PATH);
		Map<String, String> constraints1 = new HashMap<String, String>();
		constraints.put("name", destination);
		constraints.put("path",Controller.CURRENT_PATH);
		ArrayList<UserFile> files = testMongo.getFiles(constraints);
		ArrayList<UserFile> files1 = testMongo.getFiles(constraints1);
		if((files.get(0).isDirectory()==false)&&(files1.get(0).isDirectory()==true)){

			files.get(0).setPath(files1.get(0).getPath()+files1.get(0).getName());
			Date date = new Date();
			files.get(0).setDate_created(date);
			files.get(0).setDate_updated(date);

			files.get(0).setTimestamp(date);
			files.get(0).setUser_created(1);
			files.get(0).setUser_updated(2);

			mongoConnect.updateCommon(files.get(0));

			result.add(ICommand.SUCCESS);

		}

		else if((files.get(0).isDirectory()==true)&&(files1.get(0).isDirectory()==false)){

			result.add(ICommand.FAILURE);
			result.add("folder can't be move to file");


		}
		else if((files.get(0).isDirectory()==false)&&(files1.get(0).isDirectory()==false)){

			//String SourcePath=files.get(0).getPath();
			//String DestinationPath=files1.get(0).getPath();

			String sourceData=files.get(0).getData();
			String destinationData=sourceData;


			files1.get(0).setData(destinationData);
			files1.get(0).setPath(files1.get(0).getPath());
			mongoConnect.updateCommon(files1.get(0));

			result.add(ICommand.SUCCESS);	

		}

			else if(files.size()>0&&files1.size()<0)
			{
				String sourceData=files.get(0).getData();
				String destinationData=sourceData;
				UserFile u=new UserFile();
				u.setName("destination");
				u.setData(destinationData);
				u.setDate_created(null);
				u.setDate_updated(null);
				u.setFile_size(0);
				u.setFiletypeId(1);
				u.setPath("/kanchu/desktop");
				u.setData(sourceData);

				WriteResult wr = mongoConnect.createFile(u);
				mongoConnect.updateCommon(u);
				if(wr.getError().equals(null))
				{

					result.add(ICommand.SUCCESS);
				}


				else{
					result.add(ICommand.FAILURE);
					result.add("file does not located");

				}
			}
		


		else if((files.get(0).isDirectory()==true)&&(files1.get(0).isDirectory()==true)){

         String sourcepath=files.get(0).getPath();
         

		}



		return result;}


}


//move file to folder will update path 
//move folder to file will give error
//move file to file  will update path also
//move folder to folder  path update
//