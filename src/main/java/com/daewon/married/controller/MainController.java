package com.daewon.married.controller;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.dto.VoteDTO;
import com.daewon.married.service.MemberService;
import com.daewon.married.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 회원 가입
     * @param memberDTO
     * @return
     */
    @PostMapping("/register")
    public Long register(@RequestBody MemberDTO memberDTO) {
        return memberService.register(memberDTO);
    }

    /**
     * 매칭 결과 조회
     * @param empId
     * @return
     */
    @GetMapping("/matchResult")
    public List<String> matchResult(String empId) {
        return voteService.selectMatchingResultByEmpId(empId);
    }

    /**
     * 이상형 투표
     * @param voteDTO
     * @return
     */
    @PostMapping("/voteRegister")
    public Long voteRegister(@RequestBody VoteDTO voteDTO) {
        voteService.voteRecord(voteDTO);
        return voteDTO.getVno();
    }

    /**
     * 회원 정보 수정
     * @param memberDTO
     * @return
     */
    @PostMapping("/member/modify")
    public Long memberModify(@RequestBody MemberDTO memberDTO) {
        return memberService.updateMember(memberDTO);
    }
}
