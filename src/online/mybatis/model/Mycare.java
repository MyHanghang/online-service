package online.mybatis.model;

public class Mycare {
         /*  字段名 主键*/
          private String id;      
         /*  字段名 我的id*/
          private String myid;      
         /*  字段名 关注id*/
          private String careid;      
         /*  字段名 类别(用户关注，视频关注，收藏)*/
          private String type;      
         /*  字段名 字段1*/
          private String spare1;      
         /*  字段名 字段2*/
          private String spare2;      
         /*  字段名 字段3*/
          private String spare3;      
         public String getId() {
       	 return id;
        }
        public void setId(String id) {
       	 this.id = id == null ? null : id.trim();
  	  }
 
         public String getMyid() {
       	 return myid;
        }
        public void setMyid(String myid) {
       	 this.myid = myid == null ? null : myid.trim();
  	  }
 
         public String getCareid() {
       	 return careid;
        }
        public void setCareid(String careid) {
       	 this.careid = careid == null ? null : careid.trim();
  	  }
 
         public String getType() {
       	 return type;
        }
        public void setType(String type) {
       	 this.type = type == null ? null : type.trim();
  	  }
 
         public String getSpare1() {
       	 return spare1;
        }
        public void setSpare1(String spare1) {
       	 this.spare1 = spare1 == null ? null : spare1.trim();
  	  }
 
         public String getSpare2() {
       	 return spare2;
        }
        public void setSpare2(String spare2) {
       	 this.spare2 = spare2 == null ? null : spare2.trim();
  	  }
 
         public String getSpare3() {
       	 return spare3;
        }
        public void setSpare3(String spare3) {
       	 this.spare3 = spare3 == null ? null : spare3.trim();
  	  }
 
  
  
	@Override
	public String toString() {
	
			return "Mycare ["
				+ ",id=" + id 
				+ ",myid=" + myid 
				+ ",careid=" + careid 
				+ ",type=" + type 
				+ ",spare1=" + spare1 
				+ ",spare2=" + spare2 
				+ ",spare3=" + spare3 
										+ 
										"]";
	
	}
    
}