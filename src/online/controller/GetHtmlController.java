
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import online.mybatis.mapper.ExamVideoMapper;
import online.mybatis.model.ExamVideo;
import online.mybatis.model.ExamVideoExample;
import online.request.ExamTypeRequest;
import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 21:37
 * @Version V2.0
 */
@Controller
@RequestMapping("/common")
public class GetHtmlController  extends BaseController  {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Resource
	private ExamVideoMapper mapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/setVideo", method=RequestMethod.POST)
	@ResponseBody
	public void setVideo(@RequestBody String str) {
		HttpSession session = getSession();
		mapper.selectByPrimaryKey(str);
		//session.setAttribute("vidoeId", list.get(0).getId());
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/html", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView video(@RequestBody String str) {
    	ModelAndView mv=new   ModelAndView(str) ;		
		return mv;
	}
	

	
}
