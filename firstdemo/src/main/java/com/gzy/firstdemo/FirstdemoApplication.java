package com.gzy.firstdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *  @SpringBootApplication 标注一个主程序类， 说明是一个SpringBoot应用
 */
@EnableWebSocket
@SpringBootApplication
public class FirstdemoApplication {

    // 以jar包的方式加载
    public static void main(String[] args) {
        //启动语句
        SpringApplication.run(FirstdemoApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
