package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Review;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.ReviewNotFoundException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import com.hyeon.jpaboard.repository.ReviewRepository;
import com.hyeon.jpaboard.service.ReviewService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long saveReview(ReviewSaveDto reviewSaveDto, Long postId, String memberEamil) {
        Member member = memberRepository.findByMemberEmail(memberEamil).orElseThrow(() -> new UserNotFoundException("해당하는 회원이 없습니다."));
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new PostNotFoundException("게시물이 존재하지 않습니다."));

        Review review = ReviewSaveDto.toEntity(reviewSaveDto, post, member);
        //부모가 있음 댓글의 댓글
        if (reviewSaveDto.getParentId() != null) {
            Review parentReview = reviewRepository.findById(reviewSaveDto.getParentId()).orElse(null);
            review.setParent(parentReview);
        }
        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    @Transactional
    public Long updateReview(ReviewUpdateDto reviewUpdateDto) {
        Review updateReview = reviewRepository.findById(reviewUpdateDto.getReviewId())
                .map(review -> review.updateReview(reviewUpdateDto))
                .orElseThrow(() -> new ReviewNotFoundException("해당 하는 리뷰가 없습니다."));
        return updateReview.getId();
    }

    @Override
    public List<ReviewResponse> findReviewLists(Long postId) {
        List<Review> reviewList = reviewRepository.findAllByReviewList(postId);
        return reviewList.stream()
                .filter(child -> !child.getChild().isEmpty())
                .filter(parent -> parent.getParent() == null)
                .map(review -> new ReviewResponse(review))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("해당하는 리뷰가 없습니다."));
        reviewRepository.delete(review);
    }
}
