package online.mybatis.model;

public class TblDemo {
    private Integer id;

    private String demo1;

    private String demo2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDemo1() {
        return demo1;
    }

    public void setDemo1(String demo1) {
        this.demo1 = demo1 == null ? null : demo1.trim();
    }

    public String getDemo2() {
        return demo2;
    }

    public void setDemo2(String demo2) {
        this.demo2 = demo2 == null ? null : demo2.trim();
    }
}