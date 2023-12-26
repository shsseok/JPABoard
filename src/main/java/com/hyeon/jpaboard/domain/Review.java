package com.hyeon.jpaboard.domain;

import com.hyeon.jpaboard.domain.util.CommonDate;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private String reviewTitle;
    private String reviewContent;
    private Long reviewViews;
    @Embedded
    private CommonDate commonDate;

    @Builder
    public Review(Post post, String reviewTitle, String reviewContent, Long reviewViews, CommonDate commonDate) {
        this.post = post;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewViews = 0L;
        this.commonDate = CommonDate.builder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
    public Review updateReview(ReviewUpdateDto reviewUpdateDto)
    {
        this.reviewContent=reviewUpdateDto.getReviewContent();
        return this;
    }
}