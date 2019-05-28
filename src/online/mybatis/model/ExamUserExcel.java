package online.mybatis.model;

import java.util.Date;

public class ExamUserExcel {
    private String id;

    private String username;

    private String userpass;

    private String usertype;

    private String usergroup;

    private String userphone;

    private String useraddr;
    /**
     * 学院
     */
    private String spare3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsergroup() {
        return usergroup;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUseraddr() {
        return useraddr;
    }

    public void setUseraddr(String useraddr) {
        this.useraddr = useraddr;
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }

    @Override
    public String toString() {
        return "ExamUserExcel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", usertype='" + usertype + '\'' +
                ", usergroup='" + usergroup + '\'' +
                ", userphone='" + userphone + '\'' +
                ", useraddr='" + useraddr + '\'' +
                ", spare3='" + spare3 + '\'' +
                '}';
    }


}