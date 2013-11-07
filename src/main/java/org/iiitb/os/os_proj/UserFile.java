package org.iiitb.os.os_proj;

import java.util.Date;

public class UserFile {

	private long id;
	private String name;
	private int filetypeId;
	private String path;
	private long file_size;
	private Date timestamp;
	private Date date_created;
	private Date date_updated;
	private int user_created;
	private int user_updated;	

	private boolean isDirectory;

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFiletypeId() {
		return filetypeId;
	}

	public void setFiletypeId(int filetypeId) {
		this.filetypeId = filetypeId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}

	public int getUser_created() {
		return user_created;
	}

	public void setUser_created(int user_created) {
		this.user_created = user_created;
	}

	public int getUser_updated() {
		return user_updated;
	}

	public void setUser_updated(int user_updated) {
		this.user_updated = user_updated;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

}
