package com.daewon.married.service;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.Member;

import java.time.LocalDateTime;

public interface MemberService {

    Long register(MemberDTO memberDTO);


    default Member dtoToEntity(MemberDTO memberDTO){//수정할거 수정
        Member entity = Member.builder()
                .mno(memberDTO.getMno())
                //.empId() 자동생성
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