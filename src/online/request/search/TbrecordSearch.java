package online.request.search;
import online.request.PageRequest;
public class TbrecordSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 操作人*/
          private String oprid;      
         /*  字段名 操作时间*/
          private String oprtime;      
         /*  字段名 操作类型*/
          private String oprttype;      
         /*  字段名 操作内容*/
          private String oprmemo;      
         /*  字段名 备用字段1*/
          private String spare1;      
         /*  字段名 备用字段2*/
          private String spare2;      
         /*  字段名 备用字段3*/
          private String spare3;      
  
         public String getId() {
       	 return id;
        }
        public void setId(String id) {
       	 this.id = id == null ? null : id.trim();
  	  }
    
  
         public String getOprid() {
       	 return oprid;
        }
        public void setOprid(String oprid) {
       	 this.oprid = oprid == null ? null : oprid.trim();
  	  }
    
  
         public String getOprtime() {
       	 return oprtime;
        }
        public void setOprtime(String oprtime) {
       	 this.oprtime = oprtime == null ? null : oprtime.trim();
  	  }
    
  
         public String getOprttype() {
       	 return oprttype;
        }
        public void setOprttype(String oprttype) {
       	 this.oprttype = oprttype == null ? null : oprttype.trim();
  	  }
    
  
         public String getOprmemo() {
       	 return oprmemo;
        }
        public void setOprmemo(String oprmemo) {
       	 this.oprmemo = oprmemo == null ? null : oprmemo.trim();
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
	
			return "Tbrecord ["
				+ ",id=" + id 
				+ ",oprid=" + oprid 
				+ ",oprtime=" + oprtime 
				+ ",oprttype=" + oprttype 
				+ ",oprmemo=" + oprmemo 
				+ ",spare1=" + spare1 
				+ ",spare2=" + spare2 
				+ ",spare3=" + spare3 
										+ 
										"]";
	
	}
    
}