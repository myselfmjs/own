package com.pluto.own.configuration;
import	java.beans.BeanInfo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
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
public class ViewResolverConfiguration extends WebMvcConfigurerAdapter{
        @Bean
        public ViewResolver viewResolver(){
            InternalResourceViewResolver resolver = new InternalResourceViewResolver();
            resolver.setPrefix("/WEB-INF/view/");
            resolver.setSuffix(".jsp");
            resolver.setViewNames("*");//这里是设置该视图解析器所要匹配哪些格式的视图“*”代表匹配所有格式“
            resolver.setOrder(2);//优先级,Spring配置多个视图解析器，数字越小，优先级越高，越先匹配
            return resolver;
        }

        /**
         * 下面都是配置hymeleaf的视图解析器相关的内容
         *  对thymeleaf的视图解析器，解析到webapp下的html目录下查找对应的页面
         * @return
         */
        @Bean
        public ITemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setTemplateMode("HTML5");
            //templateResolver.setPrefix("WEB-INF/");  //在WEB-INF目录下
            templateResolver.setPrefix("classpath:/templates/"); // 在resources目录下
            templateResolver.setSuffix(".html");
            templateResolver.setCharacterEncoding("utf-8");
            templateResolver.setCacheable(false);
            return templateResolver;
        }
        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            // templateEngine
            return templateEngine;
        }
        @Bean
        public ThymeleafViewResolver viewResolverThymeLeaf() {
            ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
            viewResolver.setTemplateEngine(templateEngine());
            viewResolver.setCharacterEncoding("utf-8");
            viewResolver.setOrder(1);//设置该视图解析器优先级为1
            //下面是设置该视图解析器所要匹配哪些格式的视图"html/*", "vue/*","jsps/*","templates/*"代表匹配"html/*", "vue/*","jsps/*","templates/*"格式的所有视图
            viewResolver.setViewNames(new String[]{"html/*", "vue/*","jsps/*","templates/*"});
            return viewResolver;
        }
        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

}
