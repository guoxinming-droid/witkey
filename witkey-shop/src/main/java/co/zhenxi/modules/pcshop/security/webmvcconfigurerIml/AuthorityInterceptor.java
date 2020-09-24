package co.zhenxi.modules.pcshop.security.webmvcconfigurerIml;

import cn.hutool.system.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-01 16:06
 * @Description: AuthorityInterceptor
 **/
@Slf4j
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    //拦截的URI
    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();

    static {
        //拦截的u'ri都配到这里 需要全路径 不能使用通配符
        NOT_INTERCEPT_URI.add("/api/homePage/releaseTask");
        NOT_INTERCEPT_URI.add("/api/taskPage/collectionTask");
        NOT_INTERCEPT_URI.add("/api/taskPage/tenderWork");
        NOT_INTERCEPT_URI.add("/api/taskPage/getTaskToEcho");

        NOT_INTERCEPT_URI.add("/api/employShop/InsertEmploy");
    }

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPT_URI.contains(uri)) {
            log.info("拦截" + uri);
            HttpSession session = request.getSession();
            UserInfo userInfo = (UserInfo) session.getAttribute("users_in_the_session");
            if (userInfo == null) {
                throw new RuntimeException("用户未登陆");
            }


            //log.info("不拦截" + uri);
            return true;
        }
        log.info("不拦截" + uri);
//        HttpSession session = request.getSession();
//        UserInfo userInfo = (UserInfo) session.getAttribute("user_info_in_the_session");
//        if (userInfo == null) {
//            throw new RuntimeException("用户未登陆");
//        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv) throws Exception {
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
    }
}
