package com.pluto.own.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// springboot 2.0 import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter; // springboot 1.0
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;


/**
 * 主要配置多视图实现的视图解析器相关bean实例,将该视图解析器注册到容
 *  其实关键点在于两个：
 * 1、配置order属性:视图解析器优先级问题（就是决定先使用哪个解析器来解析视图）
 * 2、配置viewnames属性（配置对应的视图解析器解析哪些格式的视图）
 * @author ：pluto
 * @date ：Created in 2019/7/25 10:14
 */
@Configuration //用来定义 DispatcherServlet 应用上下文中的 bean
@EnableWebMvc
public class ViewResolverConfiguration implements WebMvcConfigurer {

    @Value("${spring.mvc.view.prefix}")
    private String prefix="";
    @Value("${spring.mvc.view.suffix}")
    private String suffix="";
    @Value("${spring.mvc.view.view-names}")
    private String viewNames="";

    @Value("${spring.thymeleaf.prefix}")
    private String thymeleafPrefix="";
    @Value("${spring.thymeleaf.suffix}")
    private String thymeleafSuffix="";
    @Value("${spring.thymeleaf.mode}")
    private String thymeleafMode="";
    @Value("${spring.thymeleaf.encoding}")
    private String thymeleafEncoding="";
    @Value("${spring.thymeleaf.cache}")
    private Boolean thymeleafCache=false;
    @Value("${spring.thymeleaf.view-names}")
    private String[] thymeleafViewNames={};


    @Bean
    public ViewResolver jspViewResolver(){
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(prefix);
        viewResolver.setSuffix(suffix);
        viewResolver.setViewNames(viewNames);
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

    /**
     * @Description: 注册html视图解析器
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode(thymeleafMode);
        templateResolver.setPrefix(thymeleafPrefix);
        templateResolver.setSuffix(thymeleafSuffix);
        templateResolver.setCharacterEncoding(thymeleafEncoding);
        templateResolver.setCacheable(thymeleafCache);
        return templateResolver;
    }

    /**
     * @Description: 将自定义tml视图解析器添加到模板引擎并主持到ioc
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    /**
     * @Description: Thymeleaf视图解析器配置
     */
    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(thymeleafEncoding);
        viewResolver.setViewNames(thymeleafViewNames);
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    /**
     * @Description: 配置静态文件映射
     */
    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/static/");
    }*/

}