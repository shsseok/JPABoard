package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("SELECT p,m FROM Post p " +
            "JOIN FETCH p.member m")
    List<Post> findAllWithMembers();






}
