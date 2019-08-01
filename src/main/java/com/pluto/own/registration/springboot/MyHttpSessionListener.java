package com.pluto.own.registration.springboot;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSession 监听
 * @author ：pluto
 * @date ：Created in 2019/7/26 14:05
 */
//@WebListener
public class MyHttpSessionListener implements HttpSessionListener{
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("Session 被创建");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("ServletContex初始化");
    }
}
