package com.hyeon.jpaboard.domain;

import com.hyeon.jpaboard.domain.util.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


    @Builder
    public Member(String memberEmail, String memberPassword, String memberName,Set<Authority> authorities) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.authorities = authorities;
    }
}
