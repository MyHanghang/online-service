
package online.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import online.mybatis.mapper.ExamPaperDetailMapper;
import online.mybatis.mapper.ExamPaperMapper;
import online.mybatis.mapper.ExamStockMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamPaper;
import online.mybatis.model.ExamPaperDetail;
import online.mybatis.model.ExamPaperExample;
import online.mybatis.model.ExamStock;
import online.mybatis.model.ExamStockExample;
import online.mybatis.model.Tbrecord;
import online.request.SearchRequest;
import online.util.DateUtils;
import online.util.Dict;
import online.util.NoUtils;
import online.util.PageUtils;
import online.util.UuidUtil;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:46
 * @Version V2.0
 */
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private TbrecordMapper recordMapper;
    @Resource
    private ExamPaperMapper mapper;
    @Resource
    private ExamPaperDetailMapper mapper2;
    @Resource
    private ExamStockMapper mapper1;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamPaper request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存试卷】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            //新增修改试卷
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
                if (null == request.getNum()) {
                    responseMap.put("status", "题目数量不能为空");
                    return responseMap;
                } else {
                    ExamStockExample stockExample = new ExamStockExample();
                    stockExample.createCriteria().andStocktypeEqualTo(request.getPapertype());
                    List<ExamStock> stockList = mapper1.selectByExample(stockExample);
                    int sum = stockList.size();
                    if (request.getNum() > sum) {
                        responseMap.put("status", "此类别题库数据最大为[" + sum + "],请先补充题库或者减少题目数量");
                        return responseMap;
                    } else {
                        int sumPoint = 0;
                        for (int i = 0; i < request.getNum(); i++) {
                            ExamPaperDetail detail = new ExamPaperDetail();
                            detail.setId(UuidUtil.get32UUID());
                            detail.setPaperid(request.getId());
                            detail.setStockid(stockList.get(i).getId());
                            detail.setSpare1(stockList.get(i).getTitile());
                            detail.setSpare2(stockList.get(i).getAnswer());
                            detail.setSpare3(stockList.get(i).getPoint());
                            sumPoint += Integer.valueOf(stockList.get(i).getPoint());
                            mapper2.insert(detail);
                        }
                        request.setPoint(sumPoint);
                        mapper.insertSelective(request);

                    }
                }
            }
            //操作记录
            Tbrecord record = new Tbrecord();
            record.setId(UuidUtil.get32UUID());
            record.setOprid((String) session.getAttribute("oprId"));
            record.setOprtime(DateUtils.getDate());
            record.setOprttype("");
            record.setOprmemo("发布了标题[" + request.getPapername() + "]的试卷");
            recordMapper.insert(record);
            responseMap.put("status", Dict.STATUS_00);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }

        if (logger.isInfoEnabled()) {
            logger.info("【保存试卷】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody SearchRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询试卷】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamPaperExample example = new ExamPaperExample();
            if (StringUtils.isEmpty(request.getParam1())) {
                example.createCriteria().andIdIsNotNull();
            }
            List<ExamPaper> list = mapper.selectByExample(example);
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
            logger.info("【查询试卷】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamPaper request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除试卷】请求报文:" + request.toString());
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
            logger.info("【删除试卷】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }


}
