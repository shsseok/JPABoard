package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.serviceImpl.PostSaveDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findPost(Long id);
    List<Post> findPostAll();

    List<Post> findPostByCreateAt(LocalDateTime createAt);

    List<Post> findPostByTagsCount(Long tagCount);

    List<Post> findByViewsCount(Long viewsCount);

    Long savePost(PostSaveDto postSaveDto)
    Long updatePost(Long id);
    void deletePost(Long id);
}
