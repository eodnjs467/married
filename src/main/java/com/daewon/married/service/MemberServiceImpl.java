package com.daewon.married.service;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.MarriedMemberRole;
import com.daewon.married.entity.Member;
import com.daewon.married.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /*
    회원 가입
     */
    @Transactional
    public Long register(MemberDTO memberDTO) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        Member member = Member.builder()
                .mno(memberDTO.getMno())
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .empId("MARRIED_" + random.nextInt(100)) // 수정요망 1
                .name(memberDTO.getName())
                .fromSocial(false)
                .age(memberDTO.getAge())
                .tel(memberDTO.getTel())
                .regDate(LocalDateTime.now())
                .effectiveDate(LocalDateTime.now())
                .rating("0")
                .picture(memberDTO.getPicture())
                .sal(memberDTO.getSal())
                .job(memberDTO.getJob())
                .asset(memberDTO.getAsset())
                .hobbies(memberDTO.getHobbies())
//                .memberRole(MarriedMemberRole.MEMBER)
                .build();
        member.addMemberRole(member.getEmpId(), MarriedMemberRole.MEMBER);
//        System.out.println(member.getMemberRole());

        memberRepository.save(member);
        log.info(memberDTO +"가입");

        return member.getMno();
    }

    @Override
    public String authUpdate(MemberDTO memberDTO) {
        memberRepository.findById(memberDTO.getMno()); // findByEmpId로 변경 -> empId 로 조회 쿼리 생성
        return null;
    }

}
