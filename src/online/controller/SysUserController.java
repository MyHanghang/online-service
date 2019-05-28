
package online.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import online.request.LoginRequst;
import online.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamUserMapper;
import online.mybatis.mapper.MycareMapper;
import online.mybatis.model.ExamRecord;
import online.mybatis.model.ExamUser;
import online.mybatis.model.ExamUserExample;
import online.mybatis.model.Mycare;
import online.request.UserRequest;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:11
 * @Version V2.0
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    /**
     * 学生mapper接口类
     */
    @Resource
    private ExamUserMapper mapper;

    /**
     * 学生中我的收藏mapper接口类
     */
    @Resource
    private MycareMapper mycaremapper;
    /**
     * 从session获取的id，定义为GET_ID
     */
    public static final String GET_ID = "id";

    /**
     * 添加学生的信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamUser request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存用户】请求报文:" + request.toString());
        }
        System.out.println(request.getUserpass());
        Map responseMap = new HashMap();
        try {
            //新增修改用户
            request.setUserpass(MD5.md5(request.getUserpass()));
            request.setId(NoUtils.getNo());
            System.out.println("学生输入的密码加密后为：" + MD5.md5(request.getUserpass()));
            mapper.insertSelective(request);
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【保存用户】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * 学生自己修改自己的密码
     *
     * @param pass
     * @return
     */
    @RequestMapping(value = "/updatePass", method = RequestMethod.POST)
    @ResponseBody
    public Map updatePass(@RequestBody String pass) {
        if (logger.isInfoEnabled()) {
            logger.info("【修改密码】请求报文:" + pass);
        }
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            if (null == session.getAttribute("id")) {
                responseMap.put("status", Dict.STATUS_99);
                return responseMap;
            }
            ExamUser user = new ExamUser();
            user = mapper.selectByPrimaryKey((String) session.getAttribute("id"));
            user.setUserpass(MD5.md5(pass));
            mapper.updateByPrimaryKey(user);
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【修改密码】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * 学生自己修改自己的密码
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateStudentPass", method = RequestMethod.POST)
    @ResponseBody
    public Map updateStudentPass(@RequestBody String data) {
        System.out.println("进入后台" + data);
        if (logger.isInfoEnabled()) {
            logger.info("【学生修改密码】请求报文:" + data);
        }
        JSONObject json = JSON.parseObject(data);
        String oldPass = json.getString("oldPass");
        String usePass = json.getString("userpass");
        String usePass1 = json.getString("userpass1");
        Map responseMap = new HashMap();
        HttpSession session = getSession();
        try {
            ExamUser user = mapper.selectByPrimaryKey((String) session.getAttribute(GET_ID));
            if (!MD5.md5(oldPass).equals(user.getUserpass())) {
                responseMap.put("status", Dict.STATUS_44);
            } else if (usePass.equals("") && usePass1.equals("")) {
                responseMap.put("status", Dict.STATUS_33);
            } else {
                user.setUserpass(usePass);
                mapper.updateByPrimaryKey(user);
                responseMap.put("status", Dict.STATUS_00);
            }
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【学生修改密码】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * 全局查询学生的信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody UserRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询用户】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamUserExample example = new ExamUserExample();
            if (StringUtils.isEmpty(request.getUsername())) {
                example.createCriteria().andUsertypeEqualTo(request.getUsertype());
            } else {
                example.createCriteria().andUsertypeEqualTo(request.getUsertype()).andUsernameLike(request.getUsername());
            }
            List<ExamUser> list = mapper.selectByExample(example);
            int currentPage = request.getCurrentPage();
            int count = list.size();
            //分页处理完成的数据
            responseMap.put("list", list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
            //当前 页
            responseMap.put("currentPage", currentPage);
            //总数
            responseMap.put("sumCount", count);
            //总页数
            responseMap.put("sumPage", PageUtils.getSumPage(list));
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【查询用户】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/queryStudentInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map queryStudentInfo() {
        if (logger.isInfoEnabled()) {
            logger.info("【学生查询我的资料】请求报文:");
        }
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            if (null == session.getAttribute(GET_ID)) {
                responseMap.put("status", Dict.STATUS_55);
                return responseMap;
            }
            ExamUser user = mapper.selectByPrimaryKey((String) session.getAttribute("id"));
            responseMap.put("list", user);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【加载学生个人信息报错啦】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
            return responseMap;
        }
        if (logger.isInfoEnabled()) {
            logger.info("【加载学生个人信息】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/mycare", method = RequestMethod.POST)
    @ResponseBody
    public Map mycare(@RequestBody UserRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【我的关注】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            Mycare mycare = new Mycare();
            HttpSession session = getSession();
            mycare.setMyid((String) session.getAttribute("oprId"));
            mycare.setType("关注");
            List<Mycare> mycareList = mycaremapper.selectByObj(mycare);
            List<String> list1 = new ArrayList<String>();
            for (Mycare ca : mycareList) {
                list1.add(ca.getCareid());
            }
            ExamUserExample example = new ExamUserExample();
            example.createCriteria().andIdIn(list1);
            List<ExamUser> list = mapper.selectByExample(example);
            int currentPage = request.getCurrentPage();
            int count = list.size();
            //分页处理完成的数据
            responseMap.put("list", list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
            //当前 页
            responseMap.put("currentPage", currentPage);
            //总数
            responseMap.put("sumCount", count);
            //总页数
            responseMap.put("sumPage", PageUtils.getSumPage(list));
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【我的关注】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamUser request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除用户】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            mapper.deleteByPrimaryKey(request.getId());
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【删除用户】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }


}
