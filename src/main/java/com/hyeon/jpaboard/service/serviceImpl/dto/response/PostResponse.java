package com.hyeon.jpaboard.service.serviceImpl.dto.response;

import com.hyeon.jpaboard.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long postId;
    private String postWriter;
    private String postTitle;
    private String postContent;

    public static PostResponse toDto(Post post)
    {
      return PostResponse.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postWriter(post.getMember().getMemberName())
                .build();
    }


}
