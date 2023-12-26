package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostSaveDto {
    @NotNull
    private Long memberId;
    @NotEmpty(message = "제목을 입력하세요.")
    @Length(min = 0,max = 10 ,message = "제목은 공백포함 10자 이내로 완성해야 합니다.")
    private String postTitle;
    @NotEmpty(message = "내용을 입력하세요")
    private String postContent;


    public static Post toEntity(PostSaveDto postSaveDto, Member member) {
        return Post.builder()
                .member(member)
                .postTitle(postSaveDto.getPostTitle())
                .postContent(postSaveDto.getPostContent())
                .build();
    }


}
