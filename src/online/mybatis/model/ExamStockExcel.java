package online.mybatis.model;

import java.util.Date;

public class ExamStockExcel {

    private String id;
    private String stocktype;
    private String titile;
    private String answer;
    private String point;
    private String oprid;
    private Date oprtime;
    private String spare1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStocktype() {
        return stocktype;
    }

    public void setStocktype(String stocktype) {
        this.stocktype = stocktype;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getOprid() {
        return oprid;
    }

    public void setOprid(String oprid) {
        this.oprid = oprid;
    }

    public Date getOprtime() {
        return oprtime;
    }

    public void setOprtime(Date oprtime) {
        this.oprtime = oprtime;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }


    @Override
    public String toString() {
        return "ExamStockExcel{" +
                "id='" + id + '\'' +
                ", stocktype='" + stocktype + '\'' +
                ", titile='" + titile + '\'' +
                ", answer='" + answer + '\'' +
                ", point='" + point + '\'' +
                ", oprid='" + oprid + '\'' +
                ", oprtime=" + oprtime +
                ", spare1='" + spare1 + '\'' +
                '}';
    }
}