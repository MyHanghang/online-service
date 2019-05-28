
package online.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import online.mybatis.mapper.ExamRecordDetailMapper;
import online.mybatis.mapper.ExamRecordMapper;
import online.mybatis.mapper.ExamStockMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamPaper;
import online.mybatis.model.ExamPaperDetail;
import online.mybatis.model.ExamPaperDetailExample;
import online.mybatis.model.ExamRecord;
import online.mybatis.model.ExamRecordDetail;
import online.mybatis.model.ExamRecordDetailExample;
import online.mybatis.model.ExamRecordExample;
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
 * @Date 2019/4/3 22:54
 * @Version V2.0
 */
@Controller
@RequestMapping("/record")
public class RecordController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private TbrecordMapper recordMapper;
    @Resource
    private ExamRecordMapper mapper;
    @Resource
    private ExamRecordDetailMapper mapper2;
    @Resource
    private ExamStockMapper mapper1;

    @Resource
    private ExamPaperDetailMapper mapper3;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamPaper request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存答题记录】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            //新增修改答题记录
            HttpSession session = getSession();
            ExamRecord record = new ExamRecord();
            record.setUserid((String) session.getAttribute("oprId"));
            String id = UuidUtil.get32UUID();
//        	if(StringUtils.isBlank(request.getId())){
            record.setId(id);
            record.setStarttime(DateUtils.getDate());
            request.setSpare1(DateUtils.getDate());
            record.setPaperid(request.getId());
            record.setPapername(request.getPapername());
            record.setPoint(request.getPoint());
            record.setNum(request.getNum());
            mapper.insertSelective(record);
            //保存详情
            recordDetail(record);
//        	}else{
//        		record.setEndtime(new Date());
//        		record.setSpare2(DateUtils.getDate());
//            	mapper.updateByPrimaryKey(record);
//        	}   
            Tbrecord record1 = new Tbrecord();
            record1.setId(UuidUtil.get32UUID());
            record1.setOprid((String) session.getAttribute("oprId"));
            record1.setOprtime(DateUtils.getDate());
            record1.setOprttype("");
            record1.setOprmemo("参加了名称[" + request.getPapername() + "]的试卷");
            recordMapper.insert(record1);
            responseMap.put("status", Dict.STATUS_00);
            responseMap.put("id", id);

        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }

        if (logger.isInfoEnabled()) {
            logger.info("【保存答题记录】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map update(@RequestBody ExamRecord request) {
        if (logger.isInfoEnabled()) {
            logger.info("【更新答题记录（教师评分）】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            mapper.updateByPrimaryKey(request);
            Tbrecord record1 = new Tbrecord();
            record1.setId(UuidUtil.get32UUID());
            record1.setOprid((String) session.getAttribute("oprId"));
            record1.setOprtime(DateUtils.getDate());
            record1.setOprttype("");
            record1.setOprmemo("参加了名称[" + request.getPapername() + "]的试卷，最新得分为[" + request.getAnswerpiont() + "]");
            recordMapper.insert(record1);
            responseMap.put("status", Dict.STATUS_00);

        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }

        if (logger.isInfoEnabled()) {
            logger.info("【更新答题记录（教师评分）】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    /**
     * @param request
     */
    private void recordDetail(ExamRecord request) {
        ExamPaperDetailExample detailExample = new ExamPaperDetailExample();
        detailExample.createCriteria().andPaperidEqualTo(request.getPaperid());
        List<ExamPaperDetail> paperList = mapper3.selectByExample(detailExample);
        for (ExamPaperDetail paperDetail : paperList) {
            ExamRecordDetail recordDetail = new ExamRecordDetail();
            recordDetail.setId(UuidUtil.get32UUID());
            recordDetail.setRecordid(request.getId());
            recordDetail.setTitile(paperDetail.getSpare1());//spare1
//				题目：{{x.spare1}}</br>
// 		   		分值：{{ x.spare3}}</br>
// 		   		正确答案：{{ x.spare2}}</br
            recordDetail.setPoint(Integer.valueOf(paperDetail.getSpare3()));
            recordDetail.setAnswer(paperDetail.getSpare2());
            mapper2.insert(recordDetail);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody SearchRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询答题记录】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamRecordExample example = new ExamRecordExample();
            HttpSession session = getSession();
            ExamRecord record = new ExamRecord();
            String role = (String) session.getAttribute("role");
            if ("教师".equals(role)) {
                example.createCriteria().andIdIsNotNull();
            } else {
                example.createCriteria().andUseridEqualTo((String) session.getAttribute("oprId"));
            }
            List<ExamRecord> list = mapper.selectByExample(example);
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
            responseMap.put("status", Dict.STATUS_00);

        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【查询答题记录】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamRecord request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除答题记录】请求报文:" + request.toString());
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
            logger.info("【删除答题记录】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }


}
