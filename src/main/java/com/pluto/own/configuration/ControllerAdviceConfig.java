package com.pluto.own.configuration;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.Maps;
/**
 * @ ControllerAdviceConfig 这是一个增强的 Controller
 * 可以实现三个方面的功能
 * 全局异常处理
 * 全局数据绑定
 * 全局数据预处理
 * @author ：pluto
 * @date ：Created in 2019/7/29 09:58
 */
@ControllerAdvice
public class ControllerAdviceConfig {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdviceConfig.class);
    /**
     * 全局异常处理
     * @ExceptionHandler 注解用来指明异常的处理类型，
     * 即如果这里指定为 NullpointerException，则数组越界异常就不会进到这个方法中来;
     * 配置异常会使 logback.xml 配置失效；不会自动导出错误日志
     * 可配置common-lag包下的ExceptionUtils 手动抛出异常信息
     *ExceptionUtils.getFullStackTrace(e)
     * @author: Pluto
     * @Date: 2019/7/29 10:05
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object customException(Exception e){
        logger.error(ExceptionUtils.getFullStackTrace(e));
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        Map<String,String> map =  Maps.newHashMap();
        map.put("message", msg);
        return map;

    }

    /**
     * 全局数据绑定
     * @ModelAttribute 注解标记该方法的返回数据是一个全局数据，
     * 默认情况下，这个全局数据的 key 就是返回的变量名，value 就是方法返回值，
     * 当然开发者可以通过 @ModelAttribute 注解的 name 属性去重新指定 key
     * @author: Pluto
     * @Date: 2019/7/29 10:08
    */
    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }

    /**
     * 全局数据预处理
     * @InitBinder("b") 注解表示该方法用来处理和Book和相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀.
     * @author: Pluto
     * @Date: 2019/7/29 10:23
    */
    @InitBinder("b")
    public void b(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("b.");
    }
    @InitBinder("a")
    public void a(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("a.");
    }
}
