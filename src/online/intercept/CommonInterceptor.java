/*
 *Test.java
 *chenzhiwei
 * 2019年3月30日
 */
package online.intercept;

/**
 * @ClassName b
 * @Description controller层基类，其他所有controller层必须继承它
 * @Author 我叫航航航啊
 * @Date 2019/4/4 23:43
 * @Version V2.0
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class CommonInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = Logger.getLogger(CommonInterceptor.class);
    /*
     * 利用正则映射到需要拦截的路径
     *
     * private String mappingURL;
     *
     * public void setMappingURL(String mappingURL) { this.mappingURL = mappingURL;
     * }
     */

    /**
     * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 如果返回true
     * 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("==============执行顺序: 1、preHandle=== =============");
        String uri = request.getRequestURI();
        String homeUrl = request.getContextPath();

        if (uri.indexOf("/login") >= 0) {
            return true;
        }
        String username = (String) request.getSession().getAttribute("oprId");

        if (username == null) {
            System.out.println(username);
            log.info("Interceptor：跳转到login页面！");
            request.getRequestDispatcher(homeUrl + "/login.html").forward(request, response);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        log.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     * <p>
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("==============执行顺序: 3、afterCompletion================");
    }
}