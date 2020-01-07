package com.gzy.firstdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiCaseController {

    @RequestMapping("/apicase")
    public String doApicase() {
        return "hello";
    }

}
