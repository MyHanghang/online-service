package online.request;

public class SearchRequest extends PageRequest {

    private String param1;
    private String param2;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }


    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString() + "{");
        sb.append("param1=").append(param1).append('\'');
        sb.append("param2=").append(param2).append('\'');
        sb.append("}");
        return sb.toString();

    }


}