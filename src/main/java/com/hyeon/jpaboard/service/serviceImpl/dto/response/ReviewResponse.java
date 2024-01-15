package com.hyeon.jpaboard.service.serviceImpl.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hyeon.jpaboard.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class ReviewResponse {
    private Long reviewId;
    private Long parentId;
    private String reviewWriter;
    private String reviewContent;
    private List<ReviewResponse> childList;


  public ReviewResponse(Review review)
  {
      this.reviewId=review.getId();
      this.reviewWriter=review.getMember().getMemberName();
      this.reviewContent=review.getReviewContent();
      if(review.getParent()!=null)
      {
            this.parentId=review.getParent().getId();
      }
      else
      {
            this.parentId=null;
      }
      this.childList=review.getChild().stream()
              .map(child -> new ReviewResponse(child))
              .collect(Collectors.toList());
  }





  /*  public static ReviewResponse toDto(Review review)
    {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .reviewWriter(review.getMember().getMemberName())
                .reviewList(review.getChild())
                .reviewContent(review.getReviewContent())
                .build();
    }*/
}
