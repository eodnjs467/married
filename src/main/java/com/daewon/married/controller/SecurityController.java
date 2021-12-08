package com.daewon.married.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/security/")
public class SecurityController {

    @GetMapping("/all")
    public void all() {
        log.info("외부 사용자 접속");
    }

    @GetMapping("/member")
    public String member() {
        log.info("회원 접속");
        return "redirect:/";
    }

    @GetMapping("/admin")
    public void admin(){
        log.info("관리자 접속");
    }
}
