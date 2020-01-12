package com.gzy.jpademo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
    @RequestMapping("/chatroom")
    public String chatroom() {
        return "chatroom";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }
}
