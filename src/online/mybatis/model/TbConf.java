package online.mybatis.model;

public class TbConf {
         /*  字段名 主键*/
          private String id;      
         /*  字段名 配置名称*/
          private String name;      
         /*  字段名 配置类别*/
          private String type;      
         /*  字段名 配置值*/
          private String confvalue;      
         /*  字段名 配置人*/
          private String oprid;      
         /*  字段名 配置时间*/
          private String oprtime;      
         /*  字段名 临时存放值*/
          private String tempvalue;      
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
 
         public String getType() {
       	 return type;
        }
        public void setType(String type) {
       	 this.type = type == null ? null : type.trim();
  	  }
 
         public String getConfvalue() {
       	 return confvalue;
        }
        public void setConfvalue(String confvalue) {
       	 this.confvalue = confvalue == null ? null : confvalue.trim();
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
 
         public String getTempvalue() {
       	 return tempvalue;
        }
        public void setTempvalue(String tempvalue) {
       	 this.tempvalue = tempvalue == null ? null : tempvalue.trim();
  	  }
 
  
  
	@Override
	public String toString() {
	
			return "TbConf ["
				+ ",id=" + id 
				+ ",name=" + name 
				+ ",type=" + type 
				+ ",confvalue=" + confvalue 
				+ ",oprid=" + oprid 
				+ ",oprtime=" + oprtime 
				+ ",tempvalue=" + tempvalue 
										+ 
										"]";
	
	}
    
}