package org.caps.travel.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @Description 初始化常量拦截器
 * @Param
 * @return
 **/
public class ConstantsInterceptor implements HandlerInterceptor {
    private static final String HOST_CDN = "http://139.224.117.172";

    private static final String TEMPLATE_ADMIN_LTE = "adminlte/v2.4.3";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject("adminlte", HOST_CDN + "/" + TEMPLATE_ADMIN_LTE);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
