package com.daewon.married.service;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.Member;

import java.time.LocalDateTime;
import java.util.Random;

public interface MemberService {

    Long register(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO memberDTO){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        Member entity = Member.builder()
                .mno(memberDTO.getMno())
                .empId("MARRIED_" + random.nextInt(100)) // 수정요망 1
                .name(memberDTO.getName())
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
                .build();
        return entity;
    }
}