package com.pluto.own.Design.Strategy;

import org.springframework.stereotype.Component;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/9 16:26
 *
 * 多场景实现
 *
 * Spring 在注入 bean 时 会常见唯一一个Map集合
 * 例如：该类在注入时 会创建唯一id 的 Map<String, ExportStrategy>
 * 其余 ExportStrategy 的实现都会添加到 该 Map 中
 * 可通过@Autowired 的方式调用
 */
@Component("dome01")
public class Dome01 implements ExportStrategy  {
    @Override
    public void excute() {

    }

    @Override
    public String excute(String id) {
        System.out.println("Dome01--------------: " + id);
        return id;
    }
}
