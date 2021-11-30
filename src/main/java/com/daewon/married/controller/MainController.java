package com.daewon.married.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(){
        System.out.println("success");
        return "redirect::/";
    }
}
