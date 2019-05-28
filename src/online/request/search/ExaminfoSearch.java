package online.request.search;
import online.request.PageRequest;
public class ExaminfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 题目*/
          private String name;      
         /*  字段名 选项*/
          private String content;      
         /*  字段名 答案*/
          private String answer;      
         /*  字段名 备注*/
          private String memo;      
         /*  字段名 是否删除*/
          private String isdelete;      
         /*  字段名 类别*/
          private String type;      
         /*  字段名 资源id*/
          private String sourceid;      
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
    
  
         public String getAnswer() {
       	 return answer;
        }
        public void setAnswer(String answer) {
       	 this.answer = answer == null ? null : answer.trim();
  	  }
    
  
         public String getMemo() {
       	 return memo;
        }
        public void setMemo(String memo) {
       	 this.memo = memo == null ? null : memo.trim();
  	  }
    
  
         public String getIsdelete() {
       	 return isdelete;
        }
        public void setIsdelete(String isdelete) {
       	 this.isdelete = isdelete == null ? null : isdelete.trim();
  	  }
    
  
         public String getType() {
       	 return type;
        }
        public void setType(String type) {
       	 this.type = type == null ? null : type.trim();
  	  }
    
  
         public String getSourceid() {
       	 return sourceid;
        }
        public void setSourceid(String sourceid) {
       	 this.sourceid = sourceid == null ? null : sourceid.trim();
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
	
			return "Examinfo ["
				+ ",id=" + id 
				+ ",name=" + name 
				+ ",content=" + content 
				+ ",answer=" + answer 
				+ ",memo=" + memo 
				+ ",isdelete=" + isdelete 
				+ ",type=" + type 
				+ ",sourceid=" + sourceid 
				+ ",oprid=" + oprid 
				+ ",oprtime=" + oprtime 
										+ 
										"]";
	
	}
    
}