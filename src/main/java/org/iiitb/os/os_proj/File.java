package org.iiitb.os.os_proj;

import java.util.Date;

public class File {

	private long id;
	private String name;
	private int filetypeId;
	private String timestamp;
	private Date date_created;
	private Date date_updated;
	private String user_created;
	private String user_updated;
	private String path;
	private long file_size;

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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
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

	public String getUser_created() {
		return user_created;
	}

	public void setUser_created(String user_created) {
		this.user_created = user_created;
	}

	public String getUser_updated() {
		return user_updated;
	}

	public void setUser_updated(String user_updated) {
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

}
