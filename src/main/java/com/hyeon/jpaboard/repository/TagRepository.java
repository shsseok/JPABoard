package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Likes;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByMemberAndPost(Member member, Post post);
    Boolean existsByPostIdAndMember(Long postId,Member member);

}
