package com.hyeon.jpaboard.service.serviceImpl.dto.response;


import com.hyeon.jpaboard.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private Long reviewId;
    private String reviewTitle;
    private String reviewContent;
    private Long reviewViews;

    public static ReviewResponse toDto(Review review)
    {
        return ReviewResponse.builder()
                .reviewTitle(review.getReviewTitle())
                .reviewId(review.getId())
                .reviewContent(review.getReviewContent())
                .reviewViews(review.getReviewViews())
                .build();
    }
}
