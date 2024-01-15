package com.hyeon.jpaboard.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Review;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.ReviewNotFoundException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import com.hyeon.jpaboard.repository.ReviewRepository;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.ReviewService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.ReviewResponse;

import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReviewServiceImplTest {
    @Autowired
    EntityManager em;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;

    public Member createMember() {
        Member member = Member.builder()
                .memberName("구름낀")
                .memberEmail("1234@naver.com")
                .role("USER_ROLE")
                .memberPassword("1234")
                .build();
        em.persist(member);
        return member;
    }

    public Post createPost(Member member) {
        Post post = Post.builder()
                .postContent("게시물1")
                .member(member)
                .postTitle("게시물제목1")
                .build();
        em.persist(post);
        return post;
    }

    public Review createParentReview(Member member, Post post) {
        Review review = Review.builder()
                .post(post)
                .reviewContent("부모댓글")
                .member(member)
                .build();
        em.persist(review);
        return review;
    }

    public void clear() {
        em.flush();
        em.clear();
    }

    @Test
    @Order(1)
    public void 리뷰저장() {
        //given
        Member member = createMember();
        Post post = createPost(member);
        ReviewSaveDto reviewSaveDto = new ReviewSaveDto();
        reviewSaveDto.setReviewContent("댓글 저장");
        reviewSaveDto.setParentId(null);// 처음 리뷰
        //when
        Long savedReviewId = reviewService.saveReview(reviewSaveDto, post.getId(), member.getMemberEmail());
        //then

        Review review = reviewRepository.findById(savedReviewId).orElseThrow(() -> new ReviewNotFoundException("해당 하는 리뷰가 없습니다."));
        assertThat(review.getParent()).isEqualTo(null); //최상위 리뷰이기 때문에 NULL 이 나와야함
    }

    @Test
    @Order(2)
    public void 리뷰에대한대댓글저장() {
        //given
        Member member = createMember();
        Post post = createPost(member);
        Review parentReview = createParentReview(member, post);
        ReviewSaveDto reviewSaveDto = new ReviewSaveDto();
        reviewSaveDto.setReviewContent("리뷰에 대한 대댓글 저장입니다.");
        reviewSaveDto.setParentId(parentReview.getId());// 처음 리뷰
        //when
        Long savedReviewId = reviewService.saveReview(reviewSaveDto, post.getId(), member.getMemberEmail());
        //then
        clear();
        Review childReview = reviewRepository.findById(savedReviewId).orElseThrow(() -> new ReviewNotFoundException("해당 하는 리뷰가 없습니다."));
        assertThat(childReview.getParent().getId()).isEqualTo(parentReview.getId());
    }

    @Test
    @Order(3)
    public void 대댓글리뷰전체조회() throws Exception {
        //given
        Member member = createMember();
        Post post = createPost(member);
        Review parentReview = createParentReview(member, post);
        ReviewSaveDto reviewSaveDto1 = new ReviewSaveDto();
        reviewSaveDto1.setReviewContent("리뷰에 대한 대댓글1 저장입니다.");
        reviewSaveDto1.setParentId(parentReview.getId());// 처음 리뷰
        ReviewSaveDto reviewSaveDto2 = new ReviewSaveDto();
        reviewSaveDto2.setReviewContent("리뷰에 대한 대댓글2 저장입니다.");
        reviewSaveDto2.setParentId(parentReview.getId());// 처음 리뷰
      
        reviewService.saveReview(reviewSaveDto1, post.getId(), member.getMemberEmail());
        Long parentId = reviewService.saveReview(reviewSaveDto2, post.getId(), member.getMemberEmail());
        ReviewSaveDto reviewSaveDto3 = new ReviewSaveDto();
        reviewSaveDto3.setReviewContent("리뷰에 대한 대댓글에대댓글1 저장입니다.");
        reviewSaveDto3.setParentId(parentId);// 처음 리뷰
        reviewService.saveReview(reviewSaveDto3, post.getId(), member.getMemberEmail());
        //when
        clear();
        List<ReviewResponse> reviewLists = reviewService.findReviewLists(post.getId());

        //then
        System.out.println(convertObjectToJson(reviewLists));
    }

    public String convertObjectToJson(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
