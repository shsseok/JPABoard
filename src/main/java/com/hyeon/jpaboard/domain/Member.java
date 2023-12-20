package com.hyeon.jpaboard.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    @Builder
    public Member(String memberEmail, String memberPassword, String memberName) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }
}
