package online.request.search;
import online.request.PageRequest;
public class UserInfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 账号*/
          private String userno;      
         /*  字段名 姓名*/
          private String name;      
         /*  字段名 手机号*/
          private String phone;      
         /*  字段名 密码*/
          private String pass;      
         /*  字段名 类别*/
          private String type;      
         /*  字段名 地址*/
          private String address;      
  
         public String getId() {
       	 return id;
        }
        public void setId(String id) {
       	 this.id = id == null ? null : id.trim();
  	  }
    
  
         public String getUserno() {
       	 return userno;
        }
        public void setUserno(String userno) {
       	 this.userno = userno == null ? null : userno.trim();
  	  }
    
  
         public String getName() {
       	 return name;
        }
        public void setName(String name) {
       	 this.name = name == null ? null : name.trim();
  	  }
    
  
         public String getPhone() {
       	 return phone;
        }
        public void setPhone(String phone) {
       	 this.phone = phone == null ? null : phone.trim();
  	  }
    
  
         public String getPass() {
       	 return pass;
        }
        public void setPass(String pass) {
       	 this.pass = pass == null ? null : pass.trim();
  	  }
    
  
         public String getType() {
       	 return type;
        }
        public void setType(String type) {
       	 this.type = type == null ? null : type.trim();
  	  }
    
  
         public String getAddress() {
       	 return address;
        }
        public void setAddress(String address) {
       	 this.address = address == null ? null : address.trim();
  	  }
    
  
  
	@Override
	public String toString() {
	
			return "UserInfo ["
				+ ",id=" + id 
				+ ",userno=" + userno 
				+ ",name=" + name 
				+ ",phone=" + phone 
				+ ",pass=" + pass 
				+ ",type=" + type 
				+ ",address=" + address 
										+ 
										"]";
	
	}
    
}