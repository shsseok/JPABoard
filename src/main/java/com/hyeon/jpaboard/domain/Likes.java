package com.hyeon.jpaboard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public static Likes createLikes(Member member)
    {
        return Likes.builder()
                .member(member)
                .build();
    }
    public void setPost(Post post)
    {
        this.post=post;
        post.getLikesList().add(this);
    }
}
