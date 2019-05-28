package online.mybatis.model;

public class Replyinfo {
    /**
     * 字段名 主键
     */
    private String id;
    /**
     * 字段名 回复内容
     */
    private String memo;
    /**
     * 字段名 回复人
     */
    private String oprid;
    /**
     * 字段名 回复时间
     */
    private String oprtime;
    /**
     * 字段名 视频id
     */
    private String videoid;
    /**
     * 字段名 回复父id
     */
    private String parentid;
    /**
     * 字段名 父操作人id
     */
    private String parentoprid;
    /**
     * 字段名 父操作时间
     */
    private String parentoprtime;
    /**
     * 字段名 回复类型 1-父节点 2-子节点
     */
    private String type;
    /**
     * 字段名 是否置顶
     */
    private String istop;
    /**
     * 字段名 是否点赞
     */
    private String ispraise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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

    public String getVideoid() {
        return videoid;
    }

    public void setVideoid(String videoid) {
        this.videoid = videoid == null ? null : videoid.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getParentoprid() {
        return parentoprid;
    }

    public void setParentoprid(String parentoprid) {
        this.parentoprid = parentoprid == null ? null : parentoprid.trim();
    }

    public String getParentoprtime() {
        return parentoprtime;
    }

    public void setParentoprtime(String parentoprtime) {
        this.parentoprtime = parentoprtime == null ? null : parentoprtime.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIstop() {
        return istop;
    }

    public void setIstop(String istop) {
        this.istop = istop == null ? null : istop.trim();
    }

    public String getIspraise() {
        return ispraise;
    }

    public void setIspraise(String ispraise) {
        this.ispraise = ispraise == null ? null : ispraise.trim();
    }


    @Override
    public String toString() {

        return "Replyinfo ["
                + ",id=" + id
                + ",memo=" + memo
                + ",oprid=" + oprid
                + ",oprtime=" + oprtime
                + ",videoid=" + videoid
                + ",parentid=" + parentid
                + ",parentoprid=" + parentoprid
                + ",parentoprtime=" + parentoprtime
                + ",type=" + type
                + ",istop=" + istop
                + ",ispraise=" + ispraise
                +
                "]";

    }

}