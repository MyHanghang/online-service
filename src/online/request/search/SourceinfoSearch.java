package online.request.search;
import online.request.PageRequest;
public class SourceinfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 标题*/
          private String name;      
         /*  字段名 内容*/
          private String content;      
         /*  字段名 是否删除*/
          private String isdelete;      
         /*  字段名 类别*/
          private String type;      
         /*  字段名 资源路径*/
          private String path;      
         /*  字段名 操作时间*/
          private String oprtime;      
         /*  字段名 操作人*/
          private String oprid;      
         /*  字段名 浏览次数*/
          private String sum;      
         /*  字段名 点赞次数*/
          private String praisesum;      
         /*  字段名 是否置顶*/
          private String istop;      
         /*  字段名 二维码地址*/
          private String codepath;      
  
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
    
  
         public String getPath() {
       	 return path;
        }
        public void setPath(String path) {
       	 this.path = path == null ? null : path.trim();
  	  }
    
  
         public String getOprtime() {
       	 return oprtime;
        }
        public void setOprtime(String oprtime) {
       	 this.oprtime = oprtime == null ? null : oprtime.trim();
  	  }
    
  
         public String getOprid() {
       	 return oprid;
        }
        public void setOprid(String oprid) {
       	 this.oprid = oprid == null ? null : oprid.trim();
  	  }
    
  
         public String getSum() {
       	 return sum;
        }
        public void setSum(String sum) {
       	 this.sum = sum == null ? null : sum.trim();
  	  }
    
  
         public String getPraisesum() {
       	 return praisesum;
        }
        public void setPraisesum(String praisesum) {
       	 this.praisesum = praisesum == null ? null : praisesum.trim();
  	  }
    
  
         public String getIstop() {
       	 return istop;
        }
        public void setIstop(String istop) {
       	 this.istop = istop == null ? null : istop.trim();
  	  }
    
  
         public String getCodepath() {
       	 return codepath;
        }
        public void setCodepath(String codepath) {
       	 this.codepath = codepath == null ? null : codepath.trim();
  	  }
    
  
  
	@Override
	public String toString() {
	
			return "Sourceinfo ["
				+ ",id=" + id 
				+ ",name=" + name 
				+ ",content=" + content 
				+ ",isdelete=" + isdelete 
				+ ",type=" + type 
				+ ",path=" + path 
				+ ",oprtime=" + oprtime 
				+ ",oprid=" + oprid 
				+ ",sum=" + sum 
				+ ",praisesum=" + praisesum 
				+ ",istop=" + istop 
				+ ",codepath=" + codepath 
										+ 
										"]";
	
	}
    
}