package online.request.search;
import online.request.PageRequest;
public class TbTestSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 测试*/
          private String tb01;      
         /*  字段名 合伙*/
          private String tb02;      
         /*  字段名 在家*/
          private String tb03;      
  
         public String getId() {
       	 return id;
        }
        public void setId(String id) {
       	 this.id = id == null ? null : id.trim();
  	  }
    
  
         public String getTb01() {
       	 return tb01;
        }
        public void setTb01(String tb01) {
       	 this.tb01 = tb01 == null ? null : tb01.trim();
  	  }
    
  
         public String getTb02() {
       	 return tb02;
        }
        public void setTb02(String tb02) {
       	 this.tb02 = tb02 == null ? null : tb02.trim();
  	  }
    
  
         public String getTb03() {
       	 return tb03;
        }
        public void setTb03(String tb03) {
       	 this.tb03 = tb03 == null ? null : tb03.trim();
  	  }
    
  
  
	@Override
	public String toString() {
	
			return "TbTest ["
				+ ",id=" + id 
				+ ",tb01=" + tb01 
				+ ",tb02=" + tb02 
				+ ",tb03=" + tb03 
										+ 
										"]";
	
	}
    
}