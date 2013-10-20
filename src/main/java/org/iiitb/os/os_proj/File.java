package org.iiitb.os.os_proj;

public class File {

	private long id;
	private String name;
	private int filetypeId;
	private String timestamp;
	private String date_created;
	private String date_updated;
	private String user_created;
	private String user_updated;
	private String path;
	private long file_size;

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

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public String getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(String date_updated) {
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
