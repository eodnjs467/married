package com.daewon.married.service;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.Member;
import com.daewon.married.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
    회원 가입
     */
    @Transactional
    public Long register(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        memberRepository.save(member);
        log.info(memberDTO +"가입");

        return member.getMno();
    }

}
