package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Review;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewSaveDto {
    private Long parentId;
    @NotEmpty(message = "내용을 필수로 입력하세요")
    private String reviewContent;

    public static Review toEntity(ReviewSaveDto reviewSaveDto, Post post, Member member) {

        return Review.builder()
                .member(member)
                .reviewContent(reviewSaveDto.getReviewContent())
                .post(post)
                .build();

    }
}
