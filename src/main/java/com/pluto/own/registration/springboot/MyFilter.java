package com.pluto.own.registration.springboot;

import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 创建自己的过滤器
 * @author ：pluto
 * @date ：Created in 2019/7/26 13:41
 */
//也可通过注解注册
//@ServletComponentScan
//@WebFilter(filterName = "myFilter",urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化MyFilter 过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行MyFilter 过滤器");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("销毁MyFilter 过滤器");
    }
}
