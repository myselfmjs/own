package com.pluto.own.domain.vo;

/**
 * 简单实体测试用
 * 用于 ControllerAdvie 的全局数据预处理
 * @author ：pluto
 * @date ：Created in 2019/7/29 10:18
 */
public class Book {

    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
