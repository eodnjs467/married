package com.daewon.married.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @Id @GeneratedValue
    private Long vno;

    private String empId;

    private String targetEmpId;

    private String chooseYn;

}
