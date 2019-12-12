package com.pluto.own.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/3 10:57
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/400")
    public String error400(){
        return "error/400";
    }

    @RequestMapping("/500")
    public String error500(Model model){
        Map<String,Object> map = model.asMap();
         for (Map.Entry<String,Object> entry :map.entrySet()){
             System.out.println(entry.getKey());
             System.out.println(entry.getValue());
         }
        return "error/500";

    }
}
