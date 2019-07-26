package com.pluto.own.configuration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置
 * @author ：pluto
 * @date ：Created in 2019/7/26 21:11
 */
@Configuration
public class ErrorMvcConfig implements ErrorPageRegistrar {
        @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error/404.html");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500.html");
            System.out.println(error404.getPath());
        registry.addErrorPages(error404,error500);
    }

}
