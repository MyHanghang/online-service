package online.request;

public class ExamTypeRequest extends PageRequest {
    private String id;
    private String titile;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString() + "{");
        sb.append("titile=").append(titile).append('\'');
        sb.append("}");
        return sb.toString();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
    }


}