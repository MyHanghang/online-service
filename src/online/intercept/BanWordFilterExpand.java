package online.intercept;

import online.controller.BaseController;
import online.mybatis.model.Replyinfo;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * @ClassName 敏感词和禁用词内容处理和展示封装类
 * @Description TODO
 * @Author 我叫航航航啊
 * @Date 2019/5/9 1:02
 * @Version V2.0
 **/
public class BanWordFilterExpand extends BaseController {


    public Boolean BanWordRetuenValue(String memo, Replyinfo request, HttpSession session) {
        long beginTime = System.currentTimeMillis();
        Set<String> set = BanWordsFilter.getBadWord(request.getMemo(), 2);
        if (set.size() > 0) {
            long endTime = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("【评论内容详情】:" + session.getAttribute("oprId") + "用户发表的内容为："
                        + request.getMemo() + "。敏感词库检测出敏感词为：" + set.size() + "个，包含：" + set +
                        "检测用时：" + (endTime - beginTime));
            }
            return true;
        } else {
            return false;
        }
    }
}
