package com.pluto.own.controller;

import com.pluto.own.domain.vo.Author;
import com.pluto.own.domain.vo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
        int t= 7/0;
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


    /**
     * ControllerAdvice
     *  全局数据绑定
     * @author: Pluto
     * @Date: 2019/7/29 10:11
    */
    @GetMapping("mydata")
    public String mydata(Model model){
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        int i = 1 / 0;
        return "hello controller advice mydata";
    }

    /**
     * ControllerAdvice
     * 全局数据预处理
     * 如果前台传递多个实体中包含相同的属性，
     * 可通过设置全局数据预处理的方式加上前缀
     * @author: Pluto
     * @Date: 2019/7/29 10:26
    */
    @PostMapping("/book")
    @ResponseBody
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
        return book.toString() + author.toString();
    }

    @PostMapping("/book02")
    @ResponseBody
    public String addBook02(@ModelAttribute("bb") Book book, @ModelAttribute("aa") Author author) {
        System.out.println(book);
        System.out.println(author);
        return book.toString() + author.toString();
    }
}
