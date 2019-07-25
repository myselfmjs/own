package com.pluto.own.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ：pluto
 * @date ：Created in 2019/7/24 11:08
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping
    public String hello(){
        return "Hello-hello";
    }

    @RequestMapping("/index")
    public String index(){
        return "/WEB-INF/view/index.jsp";
    }

    @RequestMapping("/index02")
    public String index02(){
        return "index";
    }

    @RequestMapping("/tem")
    public String template(){
        return "html/index";
    }

}
