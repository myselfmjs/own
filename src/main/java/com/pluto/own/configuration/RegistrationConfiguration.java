package com.pluto.own.configuration;

import com.pluto.own.registration.MyFilter;
import com.pluto.own.registration.MyServletListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 注册 Servlet、Filter、Interceptor
 * @author ：pluto
 * @date ：Created in 2019/7/26 13:35
 */
//@Configuration
public class RegistrationConfiguration {

    /**
     *修改DispatcherServlet默认配置
     * @author: Pluto
     * @Date: 2019/7/26 13:40
     */
    /*@Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.getUrlMappings().clear();
        registrationBean.addUrlMappings("*.do");
        registrationBean.addUrlMappings("*.json");
        return registrationBean;
    }*/

    /**
     * 注册Filter 过滤器
     * @author: Pluto
     * @Date: 2019/7/26 13:55
    */
   /* @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.addUrlPatterns("/*");
        filter.setFilter(new MyFilter());
        filter.setName("myFilter");
        return new FilterRegistrationBean(new MyFilter());
    }*/

    /**
     * 注册Listener 监听器
     * @author: Pluto
     * @Date: 2019/7/26 14:09
    */
    /*@Bean
    public ServletListenerRegistrationBean listenerRegistrationBean(){
        ServletListenerRegistrationBean listener = new ServletListenerRegistrationBean();
        listener.setListener(new MyServletListener());
        return listener;
    }*/


}
