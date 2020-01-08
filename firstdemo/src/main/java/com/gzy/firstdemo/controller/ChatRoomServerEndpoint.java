package com.gzy.firstdemo.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.ServerEndpoint;

@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    

}
