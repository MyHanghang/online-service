
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

import online.mybatis.mapper.ExamVideoMapper;
import online.mybatis.mapper.MycareMapper;
import online.mybatis.mapper.ReplyinfoMapper;
import online.mybatis.mapper.TbConfMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamVideo;
import online.mybatis.model.ExamVideoExample;
import online.mybatis.model.Mycare;
import online.mybatis.model.Replyinfo;
import online.mybatis.model.TbConf;
import online.mybatis.model.Tbrecord;
import online.request.ExamTypeRequest;
import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:28
 * @Version V2.0
 */
@Controller
@RequestMapping("/video")
public class VideoController  extends BaseController  {
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ExamVideoMapper mapper;
	@Resource
	private MycareMapper mycaremapper;
	@Resource
	private TbConfMapper mapper1;
	@Resource
	private TbrecordMapper recordMapper;
	@Resource
	private ReplyinfoMapper replyinfoMapper;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map save(@RequestBody ExamVideo request) {
    	if(logger.isInfoEnabled()){
			logger.info("【保存学习资源】请求报文:"+request.toString());
		}
    	Map responseMap=new HashMap();
    	try {
    		//新增修改学习资源
    		HttpSession session = getSession();
    		request.setOprid((String) session.getAttribute("oprId"));
        	DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm");         
        	format.parse(DateUtils.getDate());
        	request.setOprtime(format.parse(DateUtils.getDate()));
        	request.setSpare1(DateUtils.getDate());
        	if(StringUtils.isEmpty(request.getSpare2())) {
        		request.setSpare2("0");
        	}
    		if(!StringUtils.isEmpty(request.getId())){
	        	mapper.updateByPrimaryKey(request);
    		}else{
    			request.setId(UuidUtil.get32UUID());
				mapper.insertSelective(request);
    		}
    		//操作记录
    		Tbrecord record=new Tbrecord();
    		record.setId(UuidUtil.get32UUID());
    		record.setOprid((String) session.getAttribute("oprId"));
    		record.setOprtime(DateUtils.getDate());
    		record.setOprmemo("发布了标题["+request.getTitile()+"]的学习资源");
    		recordMapper.insert(record);
    		responseMap.put("status", Dict.STATUS_00);
    	}catch (Exception e) {
    		if(logger.isInfoEnabled()){
    			logger.info("【报错～～～】:"+e);
    		}
    		responseMap.put("status", Dict.STATUS_99);
		}
    	
		if(logger.isInfoEnabled()){
			logger.info("【保存学习资源】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/togetMore", method=RequestMethod.POST)
	@ResponseBody
	public Map togetMore() {
		if(logger.isInfoEnabled()){
			logger.info("【跳转视频播放资源详情啊】请求报文");
		}
		Map responseMap=new HashMap();
		Map map=getRequestData();
		try {
			HttpSession session = getSession();
			session.setAttribute("videoId", map.get("id"));
			ExamVideo obj=mapper.selectByPrimaryKey((String) map.get("id"));
			String num="0";
			if(!StringUtils.isEmpty(obj.getSpare2())) {
				num=obj.getSpare2();
			}

			int count=Integer.parseInt(num)+1;
			obj.setSpare2(String.valueOf(count));
			mapper.updateByPrimaryKeySelective(obj);
			//操作记录
			Tbrecord record=new Tbrecord();
			record.setId(UuidUtil.get32UUID());
			record.setOprid((String) session.getAttribute("oprId"));
			record.setOprtime(DateUtils.getDate());
			record.setOprttype("");
			record.setOprmemo("观看标题["+obj.getTitile()+"]的学习资源");
			recordMapper.insert(record);
		}catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("【报错～～～】:"+e);
			}
			responseMap.put("status", Dict.STATUS_99);
		}
		if(logger.isInfoEnabled()){
			logger.info("【跳转视频播放资源详情啊】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getMore", method=RequestMethod.POST)
	@ResponseBody
	public Map getMore() {
    	if(logger.isInfoEnabled()){
			logger.info("【获取视频播放资源详情啊】请求报文");
		}
    	Map responseMap=new HashMap();
    	try {
    		HttpSession session = getSession();
    		//获取到视频的编号
    		String videoId=(String) session.getAttribute("videoId");
    		ExamVideo obj =mapper.selectByPrimaryKey(videoId);
    		Replyinfo replyinfo=new Replyinfo();
    		replyinfo.setVideoid(videoId);
    		replyinfo.setType("父节点");
    		List<Replyinfo> replyinfoList =replyinfoMapper.selectByObj(replyinfo);   		
    		//视频信息
    		responseMap.put("videoInfo", obj);
    		//视频信息
    		responseMap.put("replyinfoList", replyinfoList);
    		//
    		TbConf conf=new TbConf();
    		mapper1.deleteByTemp();
    		conf.setId(UuidUtil.get32UUID());
    		conf.setTempvalue(obj.getPath());
    		conf.setType("temp");
    		mapper1.insert(conf);
    	
    	}catch (Exception e) {
    		if(logger.isInfoEnabled()){
    			logger.info("【报错～～～】:"+e);
    		}
    		responseMap.put("status", Dict.STATUS_99);
		}
    	
		if(logger.isInfoEnabled()){
			logger.info("【获取视频播放资源详情啊】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getPhone", method=RequestMethod.POST)
	@ResponseBody
	
	public Map getPhone() {
    	if(logger.isInfoEnabled()){
			logger.info("【获取视频播放资源详情啊】请求报文");
		}
    	Map responseMap=new HashMap();
    	try {
    		HttpSession session = getSession();
    		//获取到视频的编号
    		String videoId=(String) session.getAttribute("videoId");
    		ExamVideo obj =mapper.selectByPrimaryKey(videoId);
    		TbConf conf=new TbConf();
    	
    		conf.setType("temp");
    		List<TbConf> list=mapper1.selectByObj(conf);
    		//视频信息
    		responseMap.put("path", list.get(0).getTempvalue());
    		
    	}catch (Exception e) {
    		if(logger.isInfoEnabled()){
    			logger.info("【报错～～～】:"+e);
    		}
    		responseMap.put("status", Dict.STATUS_99);
		}
    	
		if(logger.isInfoEnabled()){
			logger.info("【获取视频播放资源详情啊】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map query(@RequestBody ExamTypeRequest request) {
    	if(logger.isInfoEnabled()){
			logger.info("【查询学习资源】请求报文:"+request.toString());
		}
		Map responseMap=new HashMap();
		try {
			ExamVideoExample example = new ExamVideoExample();
			if (StringUtils.isEmpty(request.getTitile())) {
				example.createCriteria().andIdIsNotNull();
			} else {
				example.createCriteria().andTitileLike(request.getTitile());
			}
			List<ExamVideo>  list=mapper.selectByExample(example);
			int currentPage=request.getCurrentPage();
			int count=list.size();
			//分页处理完成的数据
			responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
			//当前 页
			responseMap.put("currentPage",currentPage);
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
		if(logger.isInfoEnabled()){
			logger.info("【查询学习资源】返回报文:"+JSON.toJSONString(responseMap));
		}
		
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/myquery", method=RequestMethod.POST)
	@ResponseBody
	public Map myquery(@RequestBody ExamTypeRequest request) {
    	if(logger.isInfoEnabled()){
			logger.info("【查询学习资源】请求报文:"+request.toString());
		}
		Map responseMap=new HashMap();
		try {
			Mycare mycare=new Mycare();
			HttpSession session = getSession();
			mycare.setMyid((String) session.getAttribute("oprId"));
			mycare.setType("收藏");
			
			List<Mycare> mycareList=mycaremapper.selectByObj(mycare);
			List<String> list1=new ArrayList<String>();
			for(Mycare ca:mycareList) {
				list1.add(ca.getCareid());
			}
			ExamVideoExample example = new ExamVideoExample();
			if (StringUtils.isEmpty(request.getTitile())) {
				example.createCriteria().andIdIsNotNull().andIdIn(list1);
			} else {
				example.createCriteria().andTitileLike(request.getTitile()).andIdIn(list1);
			}
			List<ExamVideo>  list=mapper.selectByExample(example);
			int currentPage=request.getCurrentPage();
			int count=list.size();
			//分页处理完成的数据
			responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
			//当前 页
			responseMap.put("currentPage",currentPage);
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
		if(logger.isInfoEnabled()){
			logger.info("【查询学习资源】返回报文:"+JSON.toJSONString(responseMap));
		}
		
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Map del(@RequestBody ExamVideo request) {
    	if(logger.isInfoEnabled()){
			logger.info("【删除学习资源】请求报文:"+request.toString());
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
			logger.info("【删除学习资源】返回报文:"+JSON.toJSONString(responseMap));
		}
		return responseMap;
	}

	
}
