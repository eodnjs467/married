package com.daewon.married.controller;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.service.MemberService;
import com.daewon.married.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final VoteService voteService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registerForm";
    }

    @PostMapping("/register")
    public Long register(@RequestBody MemberDTO memberDTO){
        return memberService.register(memberDTO);
    }

    @GetMapping("/matchResult")
    public String[] matchResult(@RequestBody String empId){
        String[] result = voteService.selectMatchResult(voteService.match(empId));
        return result;
    }
}
