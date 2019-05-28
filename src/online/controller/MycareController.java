package online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
import online.controller.BaseController;
import online.mybatis.mapper.MycareMapper;
import online.mybatis.model.Mycare;
import online.request.search.MycareSearch;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:42
 * @Version V2.0
 */
@Controller
@RequestMapping("/Mycare")
public class MycareController  extends BaseController {
	@Resource
	private MycareMapper mapper;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map query(@RequestBody MycareSearch request) {
		System.out.println(this.getClass()+"【查询】请求参数："+request.toString());
		Map responseMap=new HashMap();
		try {
			//我的关注表 查询
			Mycare obj=new Mycare();	
			HttpSession session = getSession();
			String oprId=(String) session.getAttribute("oprId");
			request.setMyid(oprId);
			BeanUtils.copyProperties(request,obj);
			List<Mycare>  list=mapper.selectByObjLike(obj);
			int currentPage=request.getCurrentPage();
			int count=list.size();
			//分页处理完成的数据
			//responseMap.put("list",list.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
			//当前 页
		//	responseMap.put("currentPage",currentPage);
			//总数
			responseMap.put("sumCount", count);
			//responseMap.put("sumPage", PageUtils.getSumPage(list));
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    		System.out.println(this.getClass()+"【查询成功】返回参数："+JSON.toJSONString(responseMap));

		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}		
		return responseMap;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/selectOne", method=RequestMethod.POST)
	@ResponseBody
	public Map selectOne(@RequestBody String str) {
		System.out.println(this.getClass()+"【查询one】请求参数："+str);
		Map responseMap=new HashMap();
		try {
			//我的关注表 根据主键查询
			Mycare obj=mapper.selectByPrimaryKey(str);
			responseMap.put("conf",obj);	
    		System.out.println(this.getClass()+"【查询one成功】返回参数："+JSON.toJSONString(responseMap));
		} catch (Exception e) {
			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}		
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map save(@RequestBody Mycare request) {
		System.out.println(this.getClass()+"【保存】请求参数："+request.toString());
    	Map responseMap=new HashMap();
    	try {
    		HttpSession session = getSession();
			String oprId=(String) session.getAttribute("oprId");
			request.setMyid(oprId);
    		//我的关注表 新增保存操作
    		if(StringUtils.isEmpty(request.getId())) {
    			request.setId(UuidUtil.get32UUID());
    			mapper.insert(request);
    		}else {
    			mapper.updateByPrimaryKey(request);
    		}
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    		System.out.println(this.getClass()+"【保存成功】返回参数："+JSON.toJSONString(responseMap));

    	}catch (Exception e) {
    		responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "【保存】系统错误："+e.toString());
		}
    	
		return responseMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public Map del(@RequestBody Mycare request) {
		System.out.println(this.getClass()+"【删除】请求参数："+request.toString());
    	Map responseMap=new HashMap();
    	try {
    		HttpSession session = getSession();
			String oprId=(String) session.getAttribute("oprId");
			request.setMyid(oprId);
			List<Mycare>  list=mapper.selectByObjLike(request);
			for(Mycare obj:list) {
				mapper.deleteByPrimaryKey(obj.getId());
			}
    		//我的关注表 删除操作
    		//mapper.deleteByObj(request);
    		responseMap.put("status", Dict.KEY_SUCCUSS);
    	}catch (Exception e) {
    		responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
		}
		System.out.println(this.getClass()+"【删除成功】返回参数："+JSON.toJSONString(responseMap));

		return responseMap;
	}
}
