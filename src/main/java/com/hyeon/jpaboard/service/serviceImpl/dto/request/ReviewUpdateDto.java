package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReviewUpdateDto {
    private Long reviewId;
    private String reviewContent;
}
