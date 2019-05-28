package online.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import online.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamUserMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamUser;
import online.mybatis.model.ExamUserExample;
import online.mybatis.model.Tbrecord;
import online.request.LoginRequst;
import online.util.DateUtils;
import online.util.Dict;
import online.util.UuidUtil;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:21
 * @Version V2.0
 */
@Controller
@RequestMapping("/app")
public class LoginController extends BaseController {

    @Resource
    private ExamUserMapper userMapper;
    @Autowired
    private TbrecordMapper recordMapper;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map query(@RequestBody LoginRequst request) {
        if (logger.isInfoEnabled()) {
            logger.info("用户登录==》请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        ExamUserExample example = new ExamUserExample();
        request.setUserpass(MD5.md5(request.getUserpass()));
        example.createCriteria().andIdEqualTo(request.getUsername()).andUserpassEqualTo(request.getUserpass());
        List<ExamUser> list = userMapper.selectByExample(example);
        String code = Dict.STATUS_LOGIN;
        if (list.size() != 0) {
            String token = UuidUtil.get32UUID();
            responseMap.put("sysType", list.get(0).getUsertype());
            responseMap.put("token", token);
            responseMap.put("name", list.get(0).getUsername());
            responseMap.put("lastTime", list.get(0).getSpare1());

            // 返回登录后显示在主页的数据
            System.out.println("后台获取用户名为：" + list.get(0).getUsername());
            System.out.println("后台获取最后登录时间为：" + list.get(0).getSpare1());
            HttpSession session = getSession();
            session.setAttribute("oprId", list.get(0).getId());
            session.setAttribute("oprName", list.get(0).getUsername());
            session.setAttribute("role", list.get(0).getUsertype());
            session.setAttribute("id", list.get(0).getId());
            session.setAttribute("lastTime", list.get(0).getSpare1());
            ExamUser user = list.get(0);
            user.setLastlogin(new Date());
            user.setSpare1(DateUtils.getDate());
            user.setSpare2(token);
            userMapper.updateByPrimaryKey(user);
            // 操作记录
            Tbrecord record = new Tbrecord();
            record.setId(UuidUtil.get32UUID());
            record.setOprid((String) session.getAttribute("oprId"));
            record.setOprtime(DateUtils.getDate());
            record.setOprttype("");
            record.setOprmemo("登陆了系统");
            recordMapper.insert(record);
            if (logger.isInfoEnabled()) {
                logger.info("登陆成功！");
            }
            code = Dict.STATUS_00;
        }
        responseMap.put("status", code);

        if (logger.isInfoEnabled()) {
            logger.info("【登陆】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/index")
    @ResponseBody // 登陆方法ß
    public ModelAndView index() {
        System.out.println("触发了退出后台事件！");
        ModelAndView modelAndView = new ModelAndView("redirect:/login.html");
        return modelAndView;
    }

    /**
     * 用户退出后清楚session
     *
     * @return
     */
    @RequestMapping(value = "/outlogin")
    @ResponseBody
    public String outLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (logger.isInfoEnabled()) {
            logger.info("【注销】返回报文:" + session.getAttribute("role") + ":" + session.getAttribute("oprName") + "进行了注销操作！！");
        }
        session.removeAttribute("oprId");
        session.removeAttribute("id");
        session.removeAttribute("oprId");
        session.removeAttribute("role");
        session.removeAttribute("lastTime");
        if (logger.isInfoEnabled()) {
            logger.info("【注销】返回报文:sesssion清除成功");
        }
        return null;
    }
}
