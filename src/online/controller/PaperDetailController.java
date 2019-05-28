
package online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamPaperDetailMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamPaperDetail;
import online.mybatis.model.ExamPaperDetailExample;
import online.request.SearchRequest;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:29
 * @Version V2.0
 */
@Controller
@RequestMapping("/paperDetail")
public class PaperDetailController  extends BaseController  {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private TbrecordMapper recordMapper;
	@Resource
	private ExamPaperDetailMapper mapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map save(@RequestBody ExamPaperDetail request) {
    	if(logger.isInfoEnabled()){
			logger.info("【保存试卷详情】请求报文:"+request.toString());
		}
    	Map responseMap=new HashMap();
    	try {
    		if(!StringUtils.isEmpty(request.getId())){
	        	mapper.updateByPrimaryKey(request);
    		}else{
				request.setId(UuidUtil.get32UUID());
				mapper.insertSelective(request);
    		}
    		responseMap.put("status", Dict.STATUS_00);
    	}catch (Exception e) {
    		if(logger.isInfoEnabled()){
    			logger.info("【报错～～～】:"+e);
    		}
    		responseMap.put("status", Dict.STATUS_99);
		}
    	
		if(logger.isInfoEnabled()){
			logger.info("【保存试卷详情】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map query(@RequestBody SearchRequest request) {
    	if(logger.isInfoEnabled()){
			logger.info("【查询试卷详情】请求报文:"+request.toString());
		}
		Map responseMap=new HashMap();
		try {
			ExamPaperDetailExample example = new ExamPaperDetailExample();
			example.createCriteria().andPaperidEqualTo(request.getParam1());
			List<ExamPaperDetail>  list=mapper.selectByExample(example);
			responseMap.put("list",list);
//			int currentPage=request.getCurrentPage();
//			int count=list.size();
//			//分页处理完成的数据
//			responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
//			//当前 页
//			responseMap.put("currentPage",currentPage);
//			//总数
//			responseMap.put("sumCount", count);
//			//总页数
//			responseMap.put("sumPage", PageUtils.getSumPage(list));
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("【报错～～～】:" + e);
			}
			responseMap.put("status", Dict.STATUS_99);
		}
		if(logger.isInfoEnabled()){
			logger.info("【查询试卷详情】返回报文:"+JSON.toJSONString(responseMap));
		}
		
		return responseMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Map del(@RequestBody ExamPaperDetail request) {
    	if(logger.isInfoEnabled()){
			logger.info("【删除试卷详情】请求报文:"+request.toString());
		}
    	Map responseMap=new HashMap();
    	try {
    		mapper.deleteByPrimaryKey(request.getId());
    		responseMap.put("status", Dict.STATUS_00);
    	}catch (Exception e) {
    		if(logger.isInfoEnabled()){
    			logger.info("【报错～～～】:"+e);
    		}
    		responseMap.put("status", Dict.STATUS_99);
		}
		if(logger.isInfoEnabled()){
			logger.info("【删除试卷详情】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}

	
}
