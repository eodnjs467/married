package com.daewon.married.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

    private Long mno;

    private String name;

    private String age;

    private String tel;

    private String rating;

    private String picture;

    private String sal;

    private String job;

    private String asset;

    private String hobbies;
}
