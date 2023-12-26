package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Review;
import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewSaveDto {
    private Long userId;
    private Long postId;
    private String reviewTitle;
    private String reviewContent;
    @Builder
    public static Review toEntity(ReviewSaveDto reviewSaveDto, Post post)
    {
        return Review.builder()
                .reviewTitle(reviewSaveDto.getReviewTitle())
                .reviewContent(reviewSaveDto.getReviewContent())
                .post(post)
                .build();
    }
}
