package com.daewon.married.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Note {

    @Id @GeneratedValue
    private Long num;

    private String title;

    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_email")
    private String writer;//member 에서  id가 email로 잡혀있어야ㅐ하는데 음 .. 조인컬럼이 뭐 어케 안ㄷ ㅚ나?

    private LocalDateTime regDate, modDate;

    public void changeTitle(String title) {
        this.title = title;
        this.modDate = LocalDateTime.now();
    }

    public void changeContent(String content) {
        this.content = content;
        this.modDate = LocalDateTime.now();
    }
}
