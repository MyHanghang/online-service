package online.request;

public class VideoRequest extends PageRequest {


	private String titile;

   


	public String getTitle() {
		return titile;
	}


	public void setTitle(String titile) {
		this.titile = titile;
	}


	


	@Override
    public String toString(){
    	final StringBuilder sb=new StringBuilder(super.toString()+"{");

    	sb.append("titile=").append(titile).append('\'');
     	sb.append("}");
    	return sb.toString();
    	
    }
  

}