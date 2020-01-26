package com.gzy.oceanblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageViewConroller {

    @RequestMapping("/tag")
    private String tag(){
        return "tag";
    }

    @RequestMapping("/category")
    private String category(){
        return "category";
    }

    @RequestMapping("/about")
    private String about(){
        return "about";
    }

    @RequestMapping("/admin")
    private String admin(){
        return "admin";
    }

    @RequestMapping("/admin/editmd")
    private String editmd(){
        return "editmd";
    }

    @RequestMapping("/admin/edit")
    private String edit(){
        return "edit";
    }

    @RequestMapping("/blog")
    private  String blog(){
        return "blog";
    }

}
