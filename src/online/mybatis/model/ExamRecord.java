package online.mybatis.model;

import java.util.Date;

public class ExamRecord {
    @Override
    public String toString() {
        return "ExamRecord{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", paperid='" + paperid + '\'' +
                ", papername='" + papername + '\'' +
                ", num=" + num +
                ", point=" + point +
                ", answerpiont=" + answerpiont +
                ", memo='" + memo + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime=" + endtime +
                ", spare1='" + spare1 + '\'' +
                ", spare2='" + spare2 + '\'' +
                ", spare3='" + spare3 + '\'' +
                ", spare4='" + spare4 + '\'' +
                ", spare5='" + spare5 + '\'' +
                '}';
    }

    private String id;

    private String userid;

    private String paperid;

    private String papername;

    private Integer num;

    private Integer point;

    private Integer answerpiont;

    private String memo;

    private String starttime;

    private Date endtime;

    private String spare1;

    private String spare2;

    private String spare3;

    private String spare4;

    private String spare5;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid == null ? null : paperid.trim();
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername == null ? null : papername.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getAnswerpiont() {
        return answerpiont;
    }

    public void setAnswerpiont(Integer answerpiont) {
        this.answerpiont = answerpiont;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1 == null ? null : spare1.trim();
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2 == null ? null : spare2.trim();
    }

    public String getSpare3() {
        return spare3;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3 == null ? null : spare3.trim();
    }

    public String getSpare4() {
        return spare4;
    }

    public void setSpare4(String spare4) {
        this.spare4 = spare4 == null ? null : spare4.trim();
    }

    public String getSpare5() {
        return spare5;
    }

    public void setSpare5(String spare5) {
        this.spare5 = spare5 == null ? null : spare5.trim();
    }
}