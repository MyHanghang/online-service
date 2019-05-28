
package online.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamTypeMapper;
import online.mybatis.model.ExamType;
import online.mybatis.model.ExamTypeExample;
import online.request.ExamTypeRequest;
import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;

/**
 * @ClassName b
 * @Description SysTypeController类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:07
 * @Version V2.0
 */
@Controller
@RequestMapping("/sys")
public class SysTypeController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private ExamTypeMapper mapper;


    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamType request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存类别】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamType obj = new ExamType();
            //新增修改类别
            if (!StringUtils.isEmpty(request.getId())) {
                obj.setId(request.getId());
                obj.setTitile(request.getTitile());
                HttpSession session = getSession();
                obj.setOprid((String) session.getAttribute("oprId"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                format.parse(DateUtils.getDate());
                obj.setOprtime(format.parse(DateUtils.getDate()));
                obj.setSpare1(DateUtils.getDate());
                mapper.updateByPrimaryKey(obj);
            } else {
                obj.setId(UuidUtil.get32UUID());
                obj.setTitile(request.getTitile());
                HttpSession session = getSession();
                obj.setOprid((String) session.getAttribute("oprId"));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                format.parse(DateUtils.getDate());
                obj.setOprtime(format.parse(DateUtils.getDate()));
                obj.setSpare1(DateUtils.getDate());
                mapper.insertSelective(obj);
            }
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }

        if (logger.isInfoEnabled()) {
            logger.info("【保存类别】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody ExamTypeRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询类别】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamTypeExample example = new ExamTypeExample();
            if (StringUtils.isEmpty(request.getTitile())) {
                example.createCriteria().andIdIsNotNull();
            } else {
                example.createCriteria().andTitileLike(request.getTitile());
            }
            List<ExamType> list = mapper.selectByExample(example);
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
            logger.info("【查询类别】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamType request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除类别】请求报文:" + request.toString());
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
            logger.info("【删除类别】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * 在教师上传视频时候获取视频的下拉分类信息
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    @ResponseBody
    public Map select() {
        if (logger.isInfoEnabled()) {
            logger.info("【查询类别下拉数据】请求报文:");
        }
        Map responseMap = new HashMap();
        try {
            ExamTypeExample example = new ExamTypeExample();
            example.createCriteria().andIdIsNotNull();
            List<ExamType> typelist = mapper.selectByExample(example);
            List list = new ArrayList();
            for (ExamType type : typelist) {
                list.add(type.getTitile());
            }
            responseMap.put("list", list);

        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【查询类别下拉数据】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

}
