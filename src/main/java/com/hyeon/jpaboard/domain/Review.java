package com.hyeon.jpaboard.domain;

import com.hyeon.jpaboard.domain.util.CommonDate;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewUpdateDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String reviewContent;
    @Embedded
    private CommonDate commonDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_review_id")
    private Review parent;
    @OneToMany(mappedBy = "parent",cascade = CascadeType.REMOVE)
    private List<Review> child=new ArrayList<>();

    public void setParent(Review review)
    {
        this.parent=review;
        review.getChild().add(this);
    }

    @Builder
    public Review(Post post,String reviewContent,Member member) {
        this.post = post;
        this.member=member;
        this.reviewContent = reviewContent;
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
