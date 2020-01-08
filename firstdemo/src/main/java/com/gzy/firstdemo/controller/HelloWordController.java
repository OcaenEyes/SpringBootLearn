package com.gzy.firstdemo.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class HelloWordController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/success")
    public String success(ModelMap model){
        model.addAttribute("username","gzy");
        model.addAttribute("date",new Date());
        model.addAttribute("count",12);
        return "success";
    }

    @RequestMapping("/chatroom")
    public String chat(){
        return "chatroom";
    }

}
