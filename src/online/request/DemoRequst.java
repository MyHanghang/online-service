
package online.request;

/*
 *DemoRequst.java
 *@author kylechen 
 *登陆请求参数
 * 所以request类必须重写toString()方法。
 */
public class DemoRequst {
	  	Long id;

	    String name;

	    String age;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getAge() {
	        return age;
	    }

	    public void setAge(String age) {
	        this.age = age;
	    }
	    @Override
	    public String toString(){
	    	final StringBuilder sb=new StringBuilder(super.toString()+"{");
	    	sb.append("id=").append(id).append('\'');
	    	sb.append("name=").append(name).append('\'');
	    	sb.append("age=").append(age).append('\'');
	     	sb.append("}");
	    	return sb.toString();
	    	
	    }
}
