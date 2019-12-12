package com.pluto.own.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


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
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }



    /***************************解决请求返回字符串中文乱码-BEGIN*******************************/
    @Bean
    public HttpMessageConverter<String> responseBodyStringConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return converter;
    }

    /**
     * 修改StringHttpMessageConverter默认配置
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //converters.add(responseBodyStringConverter());
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //5.将convert添加到converters当中.
        converters.add(fastJsonHttpMessageConverter);
    }
    /***************************解决请求返回字符串中文乱码-END*******************************/

}
