package com.hyeon.jpaboard.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Tag createTag(Member member)
    {

        return Tag.builder()
                .member(member)
                .build();
    }
    public void setPost(Post post)
    {
        this.post=post;
        post.getTagList().add(this);
    }


}
