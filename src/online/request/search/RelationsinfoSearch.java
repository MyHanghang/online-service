package online.request.search;
import online.request.PageRequest;
public class RelationsinfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 资源id*/
          private String sourceid;      
         /*  字段名 评论内容*/
          private String content;      
         /*  字段名 是否删除*/
          private String isdelete;      
         /*  字段名 类别  1-评论   2-关注  3-点赞  4-收藏  5-分享*/
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
    
  
         public String getSourceid() {
       	 return sourceid;
        }
        public void setSourceid(String sourceid) {
       	 this.sourceid = sourceid == null ? null : sourceid.trim();
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
	
			return "Relationsinfo ["
				+ ",id=" + id 
				+ ",sourceid=" + sourceid 
				+ ",content=" + content 
				+ ",isdelete=" + isdelete 
				+ ",type=" + type 
				+ ",oprid=" + oprid 
				+ ",oprtime=" + oprtime 
										+ 
										"]";
	
	}
    
}