package com.hyeon.jpaboard.service.serviceImpl.dto.response;

import com.hyeon.jpaboard.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
public class PostListResponse {
    private Long postId;
    private String postWriter;
    private String postTitle;
    private String postContent;

    public static PostListResponse toDto(Post post)
    {
     return PostListResponse.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postWriter(post.getMember().getMemberName())
                .build();
    }

}
