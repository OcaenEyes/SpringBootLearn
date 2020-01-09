package com.gzy.firstdemo.controller;

import com.gzy.firstdemo.entity.Room;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

}
