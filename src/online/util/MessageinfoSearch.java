package online.util;
import online.request.PageRequest;
public class MessageinfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 标题*/
          private String name;      
         /*  字段名 内容*/
          private String content;      
         /*  字段名 备注*/
          private String memo;      
         /*  字段名 类别 1-通知*/
          private String type;      
         /*  字段名 操作人*/
          private String oprid;      
         /*  字段名 操作时间*/
          private String oprtime;      
  
         public String getId() {
       	 return id;
        }
        public void setId(String id) {
       	 this.id = id == null ? null : id.trim();
  	  }
    
  
         public String getName() {
       	 return name;
        }
        public void setName(String name) {
       	 this.name = name == null ? null : name.trim();
  	  }
    
  
         public String getContent() {
       	 return content;
        }
        public void setContent(String content) {
       	 this.content = content == null ? null : content.trim();
  	  }
    
  
         public String getMemo() {
       	 return memo;
        }
        public void setMemo(String memo) {
       	 this.memo = memo == null ? null : memo.trim();
  	  }
    
  
         public String getType() {
       	 return type;
        }
        public void setType(String type) {
       	 this.type = type == null ? null : type.trim();
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
    
  
  
	@Override
	public String toString() {
	
			return "Messageinfo ["
				+ ",id=" + id 
				+ ",name=" + name 
				+ ",content=" + content 
				+ ",memo=" + memo 
				+ ",type=" + type 
				+ ",oprid=" + oprid 
				+ ",oprtime=" + oprtime 
										+ 
										"]";
	
	}
    
}