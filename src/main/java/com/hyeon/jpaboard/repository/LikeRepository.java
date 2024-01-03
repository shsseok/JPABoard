package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Likes;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {
    Optional<Likes> findLikeByMemberAndPost(Member member, Post post);

    Boolean existsLikesByPostIdAndMember(Long postId,Member member);

}
