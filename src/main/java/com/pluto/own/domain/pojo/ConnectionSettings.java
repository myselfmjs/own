package com.pluto.own.domain.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/12 14:28
 * 使用 beans的替代方法来使用properties
 */

@Component
//@ConfigurationProperties注解的beans将自动被Environment属性配置
@ConfigurationProperties("connection")
/**
 * @ConfigurationProperties(prefix = "foo")
 * 任何以foo为前缀的属性定义都会被映射到ConnectionSettings上
 */

public class ConnectionSettings {

    private String username;

    private String remoteAddress;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}
