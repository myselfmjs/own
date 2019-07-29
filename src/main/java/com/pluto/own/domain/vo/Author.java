package com.pluto.own.domain.vo;

/**
 * 简单实体测试用
 * 用于 ControllerAdvie 的全局数据预处
 * @author ：pluto
 * @date ：Created in 2019/7/29 10:18
 */
public class Author {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
