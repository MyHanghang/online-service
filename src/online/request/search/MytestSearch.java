package online.request.search;
import online.request.PageRequest;
public class MytestSearch {
         /*  字段名 主键*/
          private String id;      
         /*  字段名 测试2*/
          private String tb01;      
         /*  字段名 测试3*/
          private String tb02;      
         /*  字段名 测试4*/
          private String tb04;   
          
          private int currentPage;//当前页码

      	

      	public int getCurrentPage() {
      		return currentPage;
      	}

      	public void setCurrentPage(int currentPage) {
      		this.currentPage = currentPage;
      	}
  
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
    
  
         public String getTb04() {
       	 return tb04;
        }
        public void setTb04(String tb04) {
       	 this.tb04 = tb04 == null ? null : tb04.trim();
  	  }
    
  
  
	@Override
	public String toString() {
	
			return "Mytest ["
				+ ",id=" + id 
				+ ",tb01=" + tb01 
				+ ",tb02=" + tb02 
				+ ",tb04=" + tb04 
										+ 
										"]";
	
	}
    
}