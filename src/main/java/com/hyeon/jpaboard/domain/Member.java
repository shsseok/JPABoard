package com.hyeon.jpaboard.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(name = "member_email",unique = true)
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    @OneToMany(mappedBy = "member")
    List<Post> postList=new ArrayList<>();
    @Builder
    public Member(String memberEmail, String memberPassword, String memberName) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
    }
}
