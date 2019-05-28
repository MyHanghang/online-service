
package online.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import online.entity.RequestData;
import online.mybatis.dao.TblDemoExample;
import online.mybatis.mapper.TblDemoMapper;
import online.mybatis.model.TblDemo;
import online.request.PageRequest;
import online.util.PageUtils;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/8 20:37
 * @Version V2.0
 */

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private TblDemoMapper tblDemoMapper;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public Map query(@RequestBody PageRequest demoRequest) {
        if (logger.isInfoEnabled()) {
            logger.info("请求报文:" + demoRequest.toString());
        }
        Map responseMap = new HashMap();
        //返回map
        TblDemoExample demoExample = new TblDemoExample();
        demoExample.createCriteria().andDemo1NotEqualTo("1123");
        int currentPage = demoRequest.getCurrentPage();

        List<TblDemo> demoList = tblDemoMapper.selectByExample(demoExample);
        int count = demoList.size();
        //分页处理完成的数据
        responseMap.put("demoList", demoList.subList(PageUtils.startIndex(count, currentPage), PageUtils.endIndex(count, currentPage)));
        //当前 页
        responseMap.put("currentPage", currentPage);
        //总数
        responseMap.put("sumCount", count);
        //总页数
        responseMap.put("sumPage", PageUtils.getSumPage(demoList));

        if (logger.isInfoEnabled()) {
            logger.info("返回报文:" + JSON.toJSONString(responseMap));
        }
        return responseMap;
    }

    @SuppressWarnings({"rawtypes"})
    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        //请求参数map获取
        RequestData requestMap = new RequestData();
        Map responseMap = new HashMap();
        requestMap = this.getRequestData();
        if (logger.isInfoEnabled()) {
            logger.info("请求报文:" + requestMap.toString());
        }
        //返回map
        String jsonStrng = JSON.toJSONString(responseMap);
        if (logger.isInfoEnabled()) {
            logger.info("返回报文:" + jsonStrng);
        }
        return jsonStrng;
    }

}
