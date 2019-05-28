/*
 *Dict.java
 *chenzhiwei
 * 2018年4月14日
 */
package online.util;

/**
 * @author kelechen 系统常量
 */
public class Dict {
    /**
     * 返回成功
     */
    public static final String STATUS_00 = "0000";
    /**
     * 返回失败
     */
    public static final String STATUS_99 = "系统异常";
    /**
     * 返回session没有找到
     */
    public static final String STATUS_55 = "session不存在";
    /**
     * 旧密码不对
     */
    public static final String STATUS_44 = "4444";
    /**
     * 后台再次判断两次密码是否一致
     */
    public static final String STATUS_33 = "3333";
    /**
     * 登陆失败
     */
    public static final String STATUS_LOGIN = "账号密码不对或者账号不存在！";

    /**
     * 请求返回map 状态值
     */
    public static final String MAP_KEY = "status";
    /**
     * 请求返回map 成功值
     */
    public static final String KEY_SUCCUSS = "0000";
    /**
     *
     */
    public static final String KEY_FAIL = "9999";
    /**
     * 请求返回map 状态值
     */
    public static final String MAP_MSG = "msg";

    /**
     * 评论中含有敏感词和禁用词，返回提示状态
     */
    public static final String MEG_BANWORDS = "isban";

}
