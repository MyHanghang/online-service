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

import online.util.DateUtils;
import online.util.Dict;
import online.util.PageUtils;
import online.util.UuidUtil;
import online.controller.BaseController;
import online.mybatis.mapper.MessageinfoMapper;
import online.mybatis.model.Messageinfo;
import online.request.search.MessageinfoSearch;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 22:32
 * @Version V2.0
 */
@Controller
@RequestMapping("/Messageinfo")
public class MessageinfoController extends BaseController {
    @Resource
    private MessageinfoMapper mapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody MessageinfoSearch request) {
        System.out.println(this.getClass() + "【查询】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            //通知信息表 查询
            Messageinfo obj = new Messageinfo();
            BeanUtils.copyProperties(request, obj);
            List<Messageinfo> list = mapper.selectByObjLike(obj);
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

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public Map selectOne(@RequestBody String str) {
        System.out.println(this.getClass() + "【查询one】请求参数：" + str);
        Map responseMap = new HashMap();
        try {
            //通知信息表 根据主键查询
            Messageinfo obj = mapper.selectByPrimaryKey(str);
            responseMap.put("conf", obj);
            System.out.println(this.getClass() + "【查询one成功】返回参数：" + JSON.toJSONString(responseMap));
        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody Messageinfo request) {
        System.out.println(this.getClass() + "【保存】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            String oprName = (String) session.getAttribute("oprName");
            request.setOprid(oprName);
            request.setOprtime(DateUtils.getDate());
            //通知信息表 新增保存操作
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
            responseMap.put(Dict.MAP_MSG, "【保存】系统错误：" + e.toString());
        }

        return responseMap;
    }

    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody Messageinfo request) {
        System.out.println(this.getClass() + "【删除】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            mapper.deleteByPrimaryKey(request.getId());
            responseMap.put("status", Dict.KEY_SUCCUSS);
        } catch (Exception e) {
            responseMap.put("status", Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        System.out.println(this.getClass() + "【删除成功】返回参数：" + JSON.toJSONString(responseMap));
        return responseMap;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getQuery", method = RequestMethod.POST)
    @ResponseBody
    public Map getQuery(@RequestBody String request) {
        System.out.println(this.getClass() + "【查询】请求参数：" + request);
        Map responseMap = new HashMap();
        try {
            //通知信息表 查询
            Messageinfo obj = new Messageinfo();
            obj.setType(request);
            List<Messageinfo> list = mapper.getQuery(obj);
            responseMap.put("list", list);
            responseMap.put("status", Dict.KEY_SUCCUSS);
            System.out.println(this.getClass() + "【查询成功】返回参数：" + JSON.toJSONString(responseMap));

        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }
}
