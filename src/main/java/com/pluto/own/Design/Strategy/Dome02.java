package com.pluto.own.Design.Strategy;

import org.springframework.stereotype.Component;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/9 16:26
 * 多场景实现
 */
@Component("dome02")
public class Dome02 implements ExportStrategy {
    @Override
    public void excute() {

    }

    @Override
    public String excute(String id) {
        System.out.println("Dome02--------------: " + id);
        return id;
    }
}
