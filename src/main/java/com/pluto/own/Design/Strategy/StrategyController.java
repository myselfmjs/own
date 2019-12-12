package com.pluto.own.Design.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/9 16:57
 *
 * 策略模式 Controller
 * */
@RestController
@RequestMapping("/test")
public class StrategyController {

    @Autowired
    private ExportContextFactory executor;

    @RequestMapping("/strategy")
    public String strategy(String id){
        System.out.println(id);
        System.out.println(executor.get(id));
       return executor.get(id).excute(id);
    }
}
