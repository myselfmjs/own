package com.pluto.own.registration.springboot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 创建自己的监听器-Servlet监听
 * @author ：pluto
 * @date ：Created in 2019/7/26 14:03
 */
//@WebListener
public class MyServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContex初始化");
        System.out.println(servletContextEvent.getServletContext().getServerInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContex销毁");
    }
}
