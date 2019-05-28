
package online.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import online.mybatis.mapper.ExamRecordMapper;
import online.mybatis.model.ExamRecord;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.Null;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamRecordDetailMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamRecordDetail;
import online.mybatis.model.ExamRecordDetailExample;
import online.request.ExamTypeRequest;
import online.request.SearchRequest;
import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:59
 * @Version V2.0
 */
@Controller
@RequestMapping("/recordDetail")
public class RecordDetailController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private TbrecordMapper recordMapper;
    @Resource
    private ExamRecordDetailMapper mapper;
    @Resource
    private ExamRecordMapper examRecordMapper;
    private Integer endPoint = 0;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody ExamRecordDetail request) {
        if (logger.isInfoEnabled()) {
            logger.info("【保存答题记录详情】请求报文:" + request.toString());
        }
        ExamRecord record = new ExamRecord();
        System.out.println("初始化的record！！！！！！！！！！！！！！！111111111" + record);
        Map responseMap = new HashMap();
        try {
            //新增修改答题记录详情
            HttpSession session = getSession();
            request.setSpare1(DateUtils.getDate());
            if (!StringUtils.isEmpty(request.getId())) {
                mapper.updateByPrimaryKey(request);
                String answer = mapper.selectAnswerByRecordid(request.getId());
                String myanswer = mapper.selectMyAnswerByRecordid(request.getId());
                System.out.println("标准答案为：" + answer + "," + "我的答案为：" + myanswer + ",");
                if (answer.equals(myanswer)) {
                    endPoint += request.getPoint();
                } else {
                    System.out.println("错误一题!");
                }
            } else {
                endPoint = 0;
                request.setId(UuidUtil.get32UUID());
                session.setAttribute("record_id", request.getId());
                mapper.insertSelective(request);
            }
            record.setAnswerpiont(endPoint);
            record.setId(request.getRecordid());
            System.out.println("最后的分数：" + endPoint);
            examRecordMapper.updateByPrimaryKeyAuto(record);
            responseMap.put("status", Dict.STATUS_00);
        } catch (
                Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            endPoint = 0;
            logger.info("【保存答题记录详情】返回报文:" + JSON.toJSONString(responseMap) + "，，，同时初始化endPoint:" + endPoint);

        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody SearchRequest request) {
        if (logger.isInfoEnabled()) {
            logger.info("【查询答题记录详情】请求报文:" + request.toString());
        }
        Map responseMap = new HashMap();
        try {
            ExamRecordDetailExample example = new ExamRecordDetailExample();
            if (!StringUtils.isEmpty(request.getParam1())) {
                example.createCriteria().andRecordidEqualTo(request.getParam1());
            }
            List<ExamRecordDetail> list = mapper.selectByExample(example);
            responseMap.put("list", list);
//			int currentPage=request.getCurrentPage();
////			int count=list.size();
////			//分页处理完成的数据
////			responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
////			//当前 页
////			responseMap.put("currentPage",currentPage);
////			//总数
////			responseMap.put("sumCount", count);
////			//总页数
////			responseMap.put("sumPage", PageUtils.getSumPage(list));
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info("【报错～～～】:" + e);
            }
            responseMap.put("status", Dict.STATUS_99);
        }
        if (logger.isInfoEnabled()) {
            logger.info("【查询答题记录详情】返回报文:" + JSON.toJSONString(responseMap));
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody ExamRecordDetail request) {
        if (logger.isInfoEnabled()) {
            logger.info("【删除答题记录详情】请求报文:" + request.toString());
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
            logger.info("【删除答题记录详情】返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }


}
