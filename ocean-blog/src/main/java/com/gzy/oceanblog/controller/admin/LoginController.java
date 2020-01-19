package com.gzy.oceanblog.controller.admin;

import com.gzy.oceanblog.entity.BlogUser;
import com.gzy.oceanblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    private String login(){
        return "login";
    }

    @PostMapping("/login")
    private String login(@RequestParam String username,
                         @RequestParam String password,
                         HttpSession httpSession,
                         RedirectAttributes attributes){
        BlogUser blogUser = userService.checkUser(username,password);
        if (blogUser != null){
            blogUser.setPassword(null);
            httpSession.setAttribute("blogUser",blogUser);
            return "redirect:/admin";
        }else {
            attributes.addAttribute("message","用户名密码错误！");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/login";
    }

}
