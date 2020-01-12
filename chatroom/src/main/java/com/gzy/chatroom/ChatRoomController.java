package com.gzy.chatroom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatRoomController {
    @RequestMapping("/chat")
    public String chat(){
        return "chat";
    }
}
