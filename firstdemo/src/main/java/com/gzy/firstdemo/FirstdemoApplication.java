package com.gzy.firstdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  @SpringBootApplication 标注一个主程序类， 说明是一个SpringBoot应用
 */
@SpringBootApplication
public class FirstdemoApplication {

    // 以jar包的方式加载
    public static void main(String[] args) {
        //启动语句
        SpringApplication.run(FirstdemoApplication.class, args);
    }

}
