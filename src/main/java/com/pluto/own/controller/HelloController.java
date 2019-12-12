package com.pluto.own.controller;

import com.pluto.own.domain.pojo.ConnectionSettings;
import com.pluto.own.domain.vo.Author;
import com.pluto.own.domain.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
    public String index(){

        int t= 7/0;
        return "index.jsp";
    }

    @RequestMapping("/index02")
    public String index02(){
        return "index";
    }

    @RequestMapping("/tem")
    public String template(){
        int t= 7/0;
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


    /**
     * 测试 用bean 替代 properties 属性
     * @return
     */

    //通过获取bean 注入properties
    @Autowired
    private ConnectionSettings connection;

    @RequestMapping("/connection")
    public String getProperties(){

        System.out.println(connection.getUsername());
        System.out.println(connection.getRemoteAddress());
        return connection.getUsername();
    }
}
