package org.iiitb.os.os_proj.commands;

import java.util.ArrayList;
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
        Map<String, Object> srcConstraints = new HashMap<String, Object>();
        srcConstraints.put("name", srcPath.get(0));
        srcConstraints.put("path", srcPath.get(1));
        ArrayList<UserFile> srcFiles = mongoConnect.getFiles(srcConstraints);

        ArrayList<String> destPath = GetPath.getSearchPath(params.get(1));
        Map<String, Object> destConstraints = new HashMap<String,Object>();
        destConstraints.put("name", destPath.get(0));
        destConstraints.put("path", destPath.get(1));
        ArrayList<UserFile> destFiles = mongoConnect.getFiles(destConstraints);

        if (srcFiles.size() == 0) {
            result.add(ICommand.FAILURE);
            result.add("mv: cannot stat '" + srcPath.get(0)
                    + "': No such file or directory");
        }
        else if (!srcFiles.get(0).isDirectory()) // src is file
        {
            // move srcfile to destfile - rename filename and change path as
            // dest path
            if (destFiles.size() == 0) {
                srcFiles.get(0).setName(destPath.get(0));
                srcFiles.get(0).setPath(destPath.get(1));
            }
            else if (!destFiles.get(0).isDirectory()) // move file to
            // file(delete also)
            {
                mongoConnect.deleteFile(destPath.get(0), destPath.get(1));
                srcFiles.get(0).setName(destPath.get(0));
                srcFiles.get(0).setPath(destPath.get(1));
            }
            else // move file to folder(update path of src)
            {
                srcFiles.get(0).setPath(destPath.get(1) + destPath.get(0) + "/");
            }

            WriteResult wr = mongoConnect.updateCommon(srcFiles.get(0));
            if (wr.getError() == null)
                result.add(ICommand.SUCCESS);
            else
                result.add(ICommand.FAILURE);

        }
        else // src is a folder
        {
            // change the path of the src folder and all its contents
            if (destFiles.size() == 0) // dest folder does not exist
            {
                Map<String, Object> pathConstraint = new HashMap<String, Object>();
                String pathPattern = srcPath.get(1) + srcPath.get(0);
                pathConstraint.put("path", java.util.regex.Pattern.compile(pathPattern));
                ArrayList<UserFile> receivedFiles = mongoConnect.getFiles(pathConstraint);
                for(UserFile u: receivedFiles)
                {
                    String path = destPath.get(1) + destPath.get(0);
                    u.setPath(u.getPath().replace(pathPattern, path));
                    mongoConnect.updateCommon(u);
                }
                srcFiles.get(0).setName(destPath.get(0));
                srcFiles.get(0).setPath(destPath.get(1));
                mongoConnect.updateCommon(srcFiles.get(0));
                result.add(ICommand.SUCCESS);

            }
            else if (destFiles.get(0).isDirectory()) // dest folder exist
            {
                Map<String, Object> pathConstraint = new HashMap<String, Object>();
                String pathPattern = srcPath.get(1) + srcPath.get(0);
                pathConstraint.put("path", java.util.regex.Pattern.compile(pathPattern));
                ArrayList<UserFile> receivedFiles = mongoConnect.getFiles(pathConstraint);
                for(UserFile u: receivedFiles)
                {
                    String path = destPath.get(1) + destPath.get(0);
                    u.setPath(u.getPath().replace(pathPattern, path));
                    mongoConnect.updateCommon(u);
                }
                mongoConnect.deleteFile(srcPath.get(0), srcPath.get(1));
                result.add(ICommand.SUCCESS);

            }
            else // move folder to file - give error
            {
                result.add(ICommand.FAILURE);
                result.add("mv: cannot overwrite non-directory '"
                        + destPath.get(0) + "' with directory '"
                        + srcPath.get(0) + "'");
            }
        }
        result.add("mv command performs two different functions depending on how it is used.\nIt will either move one or more files to a different directory, or it will rename a file or directory.\nIn either case, if we are using the same filesystem, it works by simply updating references and no changes are made to the inode table.\nOtherwise, it performs a copy and delete by mallocing a new file and copying data to it.");
        return result;
    }
}
