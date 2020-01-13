package com.gzy.nettychattoom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class NettychattoomApplication implements CommandLineRunner {

    @Resource
    private ChatWebSocketRunner chatWebSocketRunner;

    public static void main(String[] args) {
        SpringApplication.run(NettychattoomApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        chatWebSocketRunner.run();
    }
}
