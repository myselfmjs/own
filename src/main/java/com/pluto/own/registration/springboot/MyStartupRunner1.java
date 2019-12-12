package com.pluto.own.registration.springboot;

import com.pluto.own.Design.Strategy.ExportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 启动加载数据_01
 * @author ：pluto
 * @date ：Created in 2019/7/26 14:36
 */
//@Component
//@Order(value = 1)
public class MyStartupRunner1 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作___01<<<<<<<<<<<<<");

    }
}
