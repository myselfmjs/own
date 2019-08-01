package com.pluto.own.configuration;

import com.pluto.own.registration.springboot.MyInterceptor01;
import com.pluto.own.registration.springboot.MyInterceptor02;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  拦截器注册
 * @author ：pluto
 * @date ：Created in 2019/7/26 14:21
 */
//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @author: Pluto
     * @Date: 2019/7/26 14:24
    */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyInterceptor01()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor02()).addPathPatterns("/**");
    }
}
