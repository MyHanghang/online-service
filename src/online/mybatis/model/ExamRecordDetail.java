package online.mybatis.model;

public class ExamRecordDetail {
    @Override
    public String toString() {
        return "ExamRecordDetail{" +
                "id='" + id + '\'' +
                ", recordid='" + recordid + '\'' +
                ", titile='" + titile + '\'' +
                ", answer='" + answer + '\'' +
                ", point=" + point +
                ", myanswer='" + myanswer + '\'' +
                ", mypoint=" + mypoint +
                ", memo='" + memo + '\'' +
                ", spare1='" + spare1 + '\'' +
                ", spare2='" + spare2 + '\'' +
                ", spare3='" + spare3 + '\'' +
                ", spare4='" + spare4 + '\'' +
                ", spare5='" + spare5 + '\'' +
                '}';
    }

    private String id;

    private String recordid;

    private String titile;

    private String answer;

    private Integer point;

    private String myanswer;

    private Integer mypoint;

    private String memo;

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

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid == null ? null : recordid.trim();
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile == null ? null : titile.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getMyanswer() {
        return myanswer;
    }

    public void setMyanswer(String myanswer) {
        this.myanswer = myanswer == null ? null : myanswer.trim();
    }

    public Integer getMypoint() {
        return mypoint;
    }

    public void setMypoint(Integer mypoint) {
        this.mypoint = mypoint;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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