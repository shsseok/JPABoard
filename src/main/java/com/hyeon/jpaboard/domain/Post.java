package com.hyeon.jpaboard.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Post {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String postTitle;
    private String postContent;
    private Long postViews;
    private LocalDateTime postCreateAt;
    private LocalDateTime postUpdateAt;

}
