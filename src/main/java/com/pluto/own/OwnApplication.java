package com.pluto.own;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;  //springboot 2.0
// import org.springframework.boot.context.web.SpringBootServletInitializer; //springboot 1.0
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages="")
//@EnableScheduling
@SpringBootApplication
public class OwnApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(OwnApplication.class);
    }

    public static void main(String[] args) {
        //SpringApplication application = new SpringApplication(OwnApplication.class);
        //application.setShowBanner(false); //禁止 Banner
        //application.run(args);
         SpringApplication.run(OwnApplication.class, args);
    }
}
