
package online.request;

/**
 * 所有request类必须重写toString()方法。
 */
public class LoginRequst {
    String oldPass;
    String userpass;
    String userpass1;
    String username;

    String token;

    String sysStatus;

    String sysType;


    public String getOldName() {
        return oldPass;
    }

    public void setOldName(String oldName) {
        this.oldPass = oldName;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSysStatus() {
        return sysStatus;
    }

    public void setSysStatus(String sysStatus) {
        this.sysStatus = sysStatus;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
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

    @Override
    public String toString() {
        return "LoginRequst{" +
                "username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", token='" + token + '\'' +
                ", sysStatus='" + sysStatus + '\'' +
                ", sysType='" + sysType + '\'' +
                ", oldName='" + oldPass + '\'' +
                '}';
    }

}
