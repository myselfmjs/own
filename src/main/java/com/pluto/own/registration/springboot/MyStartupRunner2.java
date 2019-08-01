package com.pluto.own.registration.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动加载数据_02
 * @author ：pluto
 * @date ：Created in 2019/7/26 14:36
 */
//@Component
//@Order(value = 2)
public class MyStartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作___02<<<<<<<<<<<<<");
    }
}
