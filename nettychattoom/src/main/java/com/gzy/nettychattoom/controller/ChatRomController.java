package com.gzy.nettychattoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatRomController {
    @RequestMapping("/chat")
    public String chat(){
        return "chatroom";
    }
}
