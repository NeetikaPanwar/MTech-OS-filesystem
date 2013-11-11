package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iiitb.os.os_proj.UserFile;
import org.iiitb.os.os_proj.utils.GetPath;

import com.mongodb.WriteResult;

public class Mv implements ICommand {

	public ArrayList<String> runCommand(List<String> params) {
		ArrayList<String> result = new ArrayList<String>();

		// get src and dest
		ArrayList<String> srcPath = GetPath.getSearchPath(params.get(0));
		Map<String, String> srcConstraints = new HashMap<String, String>();
		srcConstraints.put("name", srcPath.get(0));
		srcConstraints.put("path", srcPath.get(1));
		ArrayList<UserFile> srcFiles = mongoConnect.getFiles(srcConstraints);

		ArrayList<String> destPath = GetPath.getSearchPath(params.get(1));
		Map<String, String> destConstraints = new HashMap<String, String>();
		destConstraints.put("name", destPath.get(0));
		destConstraints.put("path", destPath.get(1));
		ArrayList<UserFile> destFiles = mongoConnect.getFiles(destConstraints);

		if (srcFiles.size() == 0) {
			result.add(ICommand.FAILURE);
			result.add("mv: cannot stat '" + srcPath.get(0)
					+ "': No such file or directory");
		} 
		else if (destFiles.size() == 0) // dest does not exist
		{
			if (srcFiles.get(0).isDirectory() == false) // src is a file
			{
				// move srcfile to destfile
				String sourceData = srcFiles.get(0).getData();
				UserFile usr = new UserFile();
				Date date = new Date();

				usr.setId(1234);
				usr.setName(destPath.get(0));
				usr.setFiletypeId(0);
				usr.setPath(destPath.get(1));
				usr.setFile_size(1234);
				usr.setTimestamp(date);
				usr.setDate_created(date);
				usr.setDate_updated(date);
				usr.setUser_created(1);
				usr.setUser_updated(1);
				usr.setDirectory(false);
				usr.setData(sourceData);

				WriteResult wr = mongoConnect.createFile(usr);
				if (wr.getError().equals(null))
					result.add(ICommand.SUCCESS);
				else {
					result.add(ICommand.FAILURE);
					result.add("file does not located");
				}
			}
		
		}










			//
			//		// moving file to directory
			//		if ((srcFiles.get(0).isDirectory() == false)
			//				&& (destFiles.get(0).isDirectory() == true)) {
			//
			//			srcFiles.get(0).setPath(
			//					destFiles.get(0).getPath() + destFiles.get(0).getName());
			//			Date date = new Date();
			//			srcFiles.get(0).setDate_created(date);
			//			srcFiles.get(0).setDate_updated(date);
			//
			//			srcFiles.get(0).setTimestamp(date);
			//			srcFiles.get(0).setUser_created(1);
			//			srcFiles.get(0).setUser_updated(2);
			//
			//			mongoConnect.updateCommon(srcFiles.get(0));
			//
			//			result.add(ICommand.SUCCESS);
			//
			//		}
			//		// moving folder to file- gives error
			//		else if ((srcFiles.get(0).isDirectory() == true)
			//				&& (destFiles.get(0).isDirectory() == false)) {
			//
			//			result.add(ICommand.FAILURE);
			//			result.add("folder can't be move to file");
			//
			//		}
			//		// moving file to file
			//		else if ((srcFiles.get(0).isDirectory() == false)
			//				&& (destFiles.get(0).isDirectory() == false)) {
			//
			//			// String SourcePath=files.get(0).getPath();
			//			// String DestinationPath=files1.get(0).getPath();
			//
			//			String sourceData = srcFiles.get(0).getData();
			//
			//			destFiles.get(0).setData(sourceData);
			//			destFiles.get(0).setPath(destFiles.get(0).getPath());
			//			mongoConnect.updateCommon(destFiles.get(0));
			//
			//			result.add(ICommand.SUCCESS);
			//
			//		}
			//
			//		else if ((srcFiles.get(0).isDirectory() == true)&& (destFiles.get(0).isDirectory() == true)) {
			//
			//			String sourcepath = srcFiles.get(0).getPath();
			//
			//		}
			return result;
		}
	}

	// move file to folder will update path
	// move folder to file will give error
	// move file to file will update path also
	// move folder to folder path update
	//