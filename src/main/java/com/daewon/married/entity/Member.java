package com.daewon.married.entity;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long mno;

    @Column(unique = true)
    private String email;

    private String password;

    private String empId;

    private String name;

    private boolean fromSocial;

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

//    @Enumerated(EnumType.STRING)
//    private MarriedMemberRole memberRole;


    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Map<String, MarriedMemberRole> roleMap = new HashMap<>();

    public void addMemberRole(String empId, MarriedMemberRole marriedMemberRole) {
        roleMap.put(empId, marriedMemberRole);
    }

//    private Map<String, MarriedMemberRole> roleSet = new HashMap<>();
//
//
//    public void addMemberRole(String empId, MarriedMemberRole marriedMemberRole) {
//        roleSet.put(empId, marriedMemberRole);
//    }


    public void changeEffectiveDate(Long year) {
        this.effectiveDate = LocalDateTime.now().plusYears(year);
    }

    public void changeSal(String sal) {
        this.sal = sal;
    }

}
