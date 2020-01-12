package com.gzy.chatroom;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class RunNettyApplication implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        new NettyService();
    }
}
