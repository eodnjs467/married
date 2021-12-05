package com.daewon.married.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long mno;

    @Column(unique = true)
    private String email;

    private String password;

    private String empId;

    private String name;

    private String age;

    private String tel;

    private LocalDateTime regDate;

    private LocalDateTime effectiveDate;

    private String rating;

    private String picture;

    private String sal;

    private String job;

    private String asset;

    private String hobbies;

    public void changeEffectiveDate(Long year){
        this.effectiveDate = LocalDateTime.now().plusYears(year);
    }

    public void changeSal(String sal) {
        this.sal = sal;
    }

}
