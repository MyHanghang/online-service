package online.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import online.intercept.BanWordsFilter;
import org.springframework.beans.BeanUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
import online.controller.BaseController;
import online.mybatis.mapper.ReplyinfoMapper;
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.Replyinfo;
import online.mybatis.model.Tbrecord;
import online.request.search.ReplyinfoSearch;

/**
 * @ClassName b
 * @Description ReplyinfoController接口类，包含视频评论
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:03
 * @Version V2.0
 */
@Controller
@RequestMapping("/Replyinfo")
public class ReplyinfoController extends BaseController {
    @Resource
    private ReplyinfoMapper mapper;
    @Resource
    private TbrecordMapper recordMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody ReplyinfoSearch request) {
        System.out.println(this.getClass() + "【查询】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            //获取到视频的编号
            String videoId = (String) session.getAttribute("videoId");
            request.setVideoid(videoId);
            //回复信息表 查询
            Replyinfo obj = new Replyinfo();
            BeanUtils.copyProperties(request, obj);
            List<Replyinfo> list = mapper.selectByObjLike(obj);
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
            responseMap.put("status", Dict.KEY_SUCCUSS);
            System.out.println(this.getClass() + "【查询成功】返回参数：" + JSON.toJSONString(responseMap));

        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    //	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value="/getList", method=RequestMethod.POST)
//	@ResponseBody
//	public Map getList(@RequestBody ReplyinfoSearch request) {
//		System.out.println(this.getClass()+"【查询】请求参数："+request.toString());
//		Map responseMap=new HashMap();
//		try {
//			//回复信息表 查询
//			Replyinfo obj=new Replyinfo();			     
//			BeanUtils.copyProperties(request,obj);
//			List<Replyinfo>  list=mapper.selectByObjLike(obj);
//			List<Replyinfo>  listNew=new ArrayList<Replyinfo>();
//			for(Replyinfo info:list) {
//				if("")
//				info.getType()
//			}
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
//    		responseMap.put("status", Dict.KEY_SUCCUSS);
//    		System.out.println(this.getClass()+"【查询成功】返回参数："+JSON.toJSONString(responseMap));
//
//		} catch (Exception e) {
//			responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
//			responseMap.put(Dict.MAP_MSG, "系统错误："+e.toString());
//		}		
//		return responseMap;
//	}
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public Map selectOne(@RequestBody String str) {
        System.out.println(this.getClass() + "【查询one】请求参数：" + str);
        Map responseMap = new HashMap();
        try {
            //回复信息表 根据主键查询
            Replyinfo obj = mapper.selectByPrimaryKey(str);
            responseMap.put("conf", obj);
            System.out.println(this.getClass() + "【查询one成功】返回参数：" + JSON.toJSONString(responseMap));
        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    /**
     * 用户评论功能
     *
     * @param request
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody Replyinfo request) {
        System.out.println(this.getClass() + "【提交评论】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            if (StringUtils.isEmpty(request.getId())) {
                request.setId(UuidUtil.get32UUID());
                mapper.insert(request);
            } else {
                mapper.updateByPrimaryKey(request);
            }
            responseMap.put("status", Dict.KEY_SUCCUSS);
            System.out.println(this.getClass() + "【保存成功】返回参数：" + JSON.toJSONString(responseMap));

        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "【保存评论】系统错误：" + e.toString());
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map add(@RequestBody Replyinfo request) {
        System.out.println(this.getClass() + "【添加回复】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        HttpSession session = getSession();
        long beginTime = System.currentTimeMillis();
        Set<String> set = BanWordsFilter.getBadWord(request.getMemo(), 1);
        if (set.size() > 0) {
            long endTime = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("【评论内容详情】:" + session.getAttribute("oprId") + "用户发表的内容为："
                        + request.getMemo() + "敏感词库检测出敏感词为：" + set.size() + "个，包含：" + set +
                        "检测用时：" + (endTime - beginTime));
            }
            responseMap.put("status", Dict.MEG_BANWORDS);
        } else {
            try {    //添加回复的方法
                request.setId(UuidUtil.get32UUID());
                request.setOprid((String) session.getAttribute("oprId"));
                request.setOprtime(DateUtils.getDate());
                mapper.insert(request);
                Tbrecord record1 = new Tbrecord();
                record1.setId(UuidUtil.get32UUID());
                record1.setOprid((String) session.getAttribute("oprId"));
                record1.setOprtime(DateUtils.getDate());
                record1.setOprttype("");
                if (!StringUtils.isEmpty(request.getParentid())) {
                    record1.setOprmemo("回复用户[" + request.getParentid() + "]内容为," + request.getMemo());
                } else {
                    record1.setOprmemo("发表了回复，内容为," + request.getMemo());
                }
                recordMapper.insert(record1);
                responseMap.put("status", Dict.KEY_SUCCUSS);
                System.out.println(this.getClass() + "【添加回复成功】返回参数：" + JSON.toJSONString(responseMap));

            } catch (Exception e) {
                responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
                responseMap.put(Dict.MAP_MSG, "【添加回复】系统错误：" + e.toString());
            }
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody Replyinfo request) {
        System.out.println(this.getClass() + "【删除】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            //回复信息表 删除操作
            mapper.deleteByPrimaryKey(request.getId());
            responseMap.put("status", Dict.KEY_SUCCUSS);
        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误！：" + e.toString());
        }
        System.out.println(this.getClass() + "【删除成功啦】返回参数：" + JSON.toJSONString(responseMap));

        return responseMap;
    }
}
