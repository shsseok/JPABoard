package com.hyeon.jpaboard.service.serviceImpl.dto.response;

import com.hyeon.jpaboard.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@Getter
@Builder
public class PostResponse {
    private Long postId;
    private String postWriter;
    private String postTitle;
    private String postContent;
    private Long postViews;
    private Boolean isTag;//해당 게시물을 태그를 했는지 안했는지
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public PostResponse setTag(Boolean isTag)
    {
        this.isTag=isTag;
        return this;
    }

    public static PostResponse toDto(Post post)
    {
      return PostResponse.builder()
                .postId(post.getId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .postWriter(post.getMember().getMemberName())
                .createDate(post.getCommonDate().getCreateDate())
                .updateDate(post.getCommonDate().getUpdateDate())
                .postViews(post.getPostViews())
                .build();
    }


}
