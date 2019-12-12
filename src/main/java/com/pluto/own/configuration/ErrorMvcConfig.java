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
import org.springframework.stereotype.Component;

/**
 * 错误页面配置
 * @author ：pluto
 * @date ：Created in 2019/7/26 21:11
 *
 * 1.两种方式跳转：
 *      1. ErrorController 定义一个错误页面Controller 当发生错误时 自动挑战到相对用的Mapping
 *      2. ControllerAdviceConfig 定义， 通过return 跳转
 * 2.继承 ErrorPageRegistrar 自定义错误页面
 * 3.若同时定义了 两种方式，会优先执行 ControllerAdviceConfig
 */
@Configuration
public class ErrorMvcConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        // 定义 400 错误 /error/404 为发生错误跳转地址
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND,"/error/404");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error/500");
        registry.addErrorPages(error404,error500);
    }

}
