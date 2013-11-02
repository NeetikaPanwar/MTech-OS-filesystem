package org.iiitb.os.os_proj;

/**
 * Created with IntelliJ IDEA.
 * User: navin
 * Date: 30/10/13
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int userid;
    private String username;
    private String passwordHash;
    private String salt;
    private String home;
    private Boolean isRoot;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Boolean getRoot() {
        return isRoot;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }
}
