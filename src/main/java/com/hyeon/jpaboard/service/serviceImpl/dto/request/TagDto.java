package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Likes;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Tag;
import lombok.Data;

@Data
public class TagDto {
    private Long memberId;
    private Long postId;
    public static Tag toEntity(Member member , Post post)
    {
        return  Tag.builder()
                .member(member)
                .post(post)
                .build();
    }
}
