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
import online.mybatis.mapper.TbrecordMapper;
import online.mybatis.model.ExamUser;
import online.mybatis.model.Tbrecord;
import online.request.search.TbrecordSearch;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/3 23:17
 * @Version V2.0
 */
@Controller
@RequestMapping("/Tbrecord")
public class TbrecordController extends BaseController {
    @Resource
    private TbrecordMapper mapper;
    /**
     * 从session获取的id，定义为GET_ID
     */
    public static final String GET_ID = "id";

    /**
     * 根据前端传入的参数来进行单独查询TA的操作记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody TbrecordSearch request) {
        System.out.println(this.getClass() + "【查询】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            // 个人操作记录表 查询
            Tbrecord obj = new Tbrecord();
            BeanUtils.copyProperties(request, obj);
            List<Tbrecord> list = mapper.selectByObjLike(obj);
            int count = list.size();
            if (count > 10) {
                responseMap.put("list", list.subList(0, 10));

            } else {
                responseMap.put("list", list);
            }

            // 总数
            responseMap.put("sumCount", count);

            responseMap.put("status", Dict.KEY_SUCCUSS);
            System.out.println(this.getClass() + "【查询成功】返回参数：" + JSON.toJSONString(responseMap));

        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryMyRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map queryMyRecord() {
        System.out.println(this.getClass() + "【准备查询个人操作记录】请求参数：");
        Map responseMap = new HashMap();
        try {
            HttpSession session = getSession();
            String rid = (String) session.getAttribute(GET_ID);
            System.out.println(rid);
            // 个人操作记录表 根据主键查询
            List<Tbrecord> tbrecord = mapper.selectByPrimaryKey2(rid);
            System.out.println("查询到的数据" + tbrecord);
            responseMap.put("records", tbrecord);
            System.out.println(this.getClass() + "【查询个人操作记录成功】返回参数：" + JSON.toJSONString(responseMap));
        } catch (Exception e) {

            responseMap.put("status", Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryOtherRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map queryOtherRecord() {
        System.out.println(this.getClass() + "【准备查询他人操作记录】请求参数：");
        Map responseMap = new HashMap();
        try {
            // 他人操作记录表 根据主键查询
            List<Tbrecord> otherRecord = mapper.selectOtherRecord();
            responseMap.put("records", otherRecord);
            System.out.println(this.getClass() + "【查询他人操作记录成功】返回参数：" + JSON.toJSONString(responseMap));
        } catch (Exception e) {
            responseMap.put("status", Dict.KEY_FAIL);
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
            // 个人操作记录表 根据主键查询
            Tbrecord obj = mapper.selectByPrimaryKey(str);
            responseMap.put("conf", obj);
            System.out.println(this.getClass() + "【查询one成功】返回参数：" + JSON.toJSONString(responseMap));
        } catch (Exception e) {
            responseMap.put("status", Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map save(@RequestBody Tbrecord request) {
        System.out.println(this.getClass() + "【保存】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            // 个人操作记录表 新增保存操作
            if (StringUtils.isEmpty(request.getId())) {
                request.setId(UuidUtil.get32UUID());
                mapper.insert(request);
            } else {
                mapper.updateByPrimaryKey(request);
            }
            responseMap.put("status", Dict.KEY_SUCCUSS);
            System.out.println(this.getClass() + "【保存成功】返回参数：" + JSON.toJSONString(responseMap));

        } catch (Exception e) {
            responseMap.put("status", Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "【保存】系统错误：" + e.toString());
        }

        return responseMap;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Map del(@RequestBody Tbrecord request) {
        System.out.println(this.getClass() + "【删除】请求参数：" + request.toString());
        Map responseMap = new HashMap();
        try {
            // 个人操作记录表 删除操作
            mapper.deleteByPrimaryKey(request.getId());
            responseMap.put("status", Dict.KEY_SUCCUSS);
        } catch (Exception e) {
            responseMap.put(Dict.MAP_KEY, Dict.KEY_FAIL);
            responseMap.put(Dict.MAP_MSG, "系统错误：" + e.toString());
        }
        System.out.println(this.getClass() + "【删除成功】返回参数：" + JSON.toJSONString(responseMap));

        return responseMap;
    }
}
