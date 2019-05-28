package online.request;

public class UserRequest extends PageRequest {

    private String username;
    private String usertype;


   
    public String getUsertype() {
		return usertype;
	}


	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}


	@Override
    public String toString(){
    	final StringBuilder sb=new StringBuilder(super.toString()+"{");
    	sb.append("username=").append(username).append('\'');
    	sb.append("usertype=").append(usertype).append('\'');

     	sb.append("}");
    	return sb.toString();
    	
    }
  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }


}