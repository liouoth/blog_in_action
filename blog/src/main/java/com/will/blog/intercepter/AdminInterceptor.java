package com.will.blog.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  @name adminInterceptor
 *  @Description 后台拦截器
 *  @author will7 Mao
 *  @Date  2020/9/7
 */
@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger  = LoggerFactory.getLogger(AdminInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
        }
        return super.preHandle(request,response,handler);
    }
}
