package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long> {

}
