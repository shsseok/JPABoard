package com.hyeon.jpaboard.service;


import com.hyeon.jpaboard.domain.Review;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    Long saveReview(ReviewSaveDto reviewSaveDto,Long postId,String memberEmail);
    Long updateReview(ReviewUpdateDto reviewUpdateDto);
    List<ReviewResponse> findReviewLists(Long postId);
    void deleteReview(Long id);


}
