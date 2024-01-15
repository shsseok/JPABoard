package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Review;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r,m from Review r join fetch r.member m where r.post.id=:postId")
    List<Review> findAllByReviewList(@Param("postId") Long postId);
}
