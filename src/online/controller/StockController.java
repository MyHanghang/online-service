
package online.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import online.mybatis.model.ExamStockExcel;
import online.mybatis.model.ExamUserExcel;
import online.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamStockMapper;
import online.mybatis.model.ExamStock;
import online.mybatis.model.ExamStockExample;
import online.request.ExamTypeRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:04
 * @Version V2.0
 */
@Controller
@RequestMapping("/stock")
public class StockController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private ExamStockMapper mapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamStock request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存题库】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            //新增修改题库
            HttpSession session = getSession();
            request.setOprid((String) session.getAttribute("oprId"));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            format.parse(DateUtils.getDate());
            request.setOprtime(format.parse(DateUtils.getDate()));
            request.setSpare1(DateUtils.getDate());
            if (!StringUtils.isEmpty(request.getId())) {
                mapper.updateByPrimaryKey(request);
            } else {
                request.setId(UuidUtil.get32UUID());
                mapper.insertSelective(request);
            }
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }

        if (logger.isInfoEnabled()) {
            logger.info("【保存题库】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/uploadExcel")
    @ResponseBody
    public Map uploadFileExcel(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) throws Exception {
        System.out.println("进入后台" + file.getOriginalFilename() + " === ");
        String rootPath = "E:/毕业论文/online-service/WebContent/excelstock/";
        String fileName = file.getOriginalFilename();
        String excelPath = rootPath + fileName;
        Map responseMap = new HashMap();
        FileOutputStream out;
        out = new FileOutputStream(excelPath);
        out.write(file.getBytes());
        out.flush();
        out.close();
        try {
            List<String[]> excelData = POIUtil.readExcel(new File(excelPath));
            //数据库操作
            ExamStockExcel excel = new ExamStockExcel();
            for (String[] string : excelData) {
                System.out.println("获取Excel数据：" + string);
                excel.setId(UuidUtil.get32UUID());
                excel.setStocktype(string[0]);
                excel.setTitile(string[1]);
                excel.setAnswer(string[2]);
                excel.setPoint(string[3]);
                excel.setOprid(string[4]);
                excel.setOprtime(new Date());
                excel.setSpare1(DateUtils.getDate());
                mapper.insertExcel(excel);
            }
            responseMap.put("status", Dict.STATUS_00);
        } catch (
                Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody ExamTypeRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询题库】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamStockExample example = new ExamStockExample();
            if (StringUtils.isEmpty(request.getTitile())) {
                example.createCriteria().andIdIsNotNull();
            } else {
                example.createCriteria().andTitileLike(request.getTitile());
            }
            List<ExamStock> list = mapper.selectByExample(example);
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
            logger.info("【查询题库】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamStock request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除题库】请求报文:" + request.toString());
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
            logger.info("【删除题库】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }


}
