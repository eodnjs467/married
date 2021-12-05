package com.daewon.married.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
public class Rating {

    @Id @GeneratedValue
    private Long rno;

    private int year1 = 365;

    private int year2 = 750;

    private int year3 = 1100;

}
