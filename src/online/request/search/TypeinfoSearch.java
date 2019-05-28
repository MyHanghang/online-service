package online.request.search;
import online.request.PageRequest;
public class TypeinfoSearch  extends PageRequest{
         /*  字段名 主键*/
          private String id;      
         /*  字段名 注释*/
          private String name;      
         /*  字段名 码值*/
          private String code;      
         /*  字段名 是否删除*/
          private String isdelete;      
         /*  字段名 类别*/
          private String type;      
  
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
    
  
         public String getCode() {
       	 return code;
        }
        public void setCode(String code) {
       	 this.code = code == null ? null : code.trim();
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
    
  
  
	@Override
	public String toString() {
	
			return "Typeinfo ["
				+ ",id=" + id 
				+ ",name=" + name 
				+ ",code=" + code 
				+ ",isdelete=" + isdelete 
				+ ",type=" + type 
										+ 
										"]";
	
	}
    
}