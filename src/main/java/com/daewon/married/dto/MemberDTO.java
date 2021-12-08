package com.daewon.married.dto;

import com.daewon.married.entity.MarriedMemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {//builder 정리 data 정리 , dto 필요한거 필요없는거 정리

    private Long mno;

    private String email;

    private String password;

    private String empId;

    private String name;

    private boolean fromSocial;

    private String age;

    private String tel;

    private String rating;

    private String picture;

    private String sal;

    private String job;

    private String asset;

    private String hobbies;

//    private MarriedMemberRole memberRole;
}
