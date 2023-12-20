package com.hyeon.jpaboard.repository;

import com.hyeon.jpaboard.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
