package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostListResponse;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {
    Post findPost(Long id);
    PostResponse findMemberNameWithPost(Long id);
    List<PostListResponse> findPostAll();
    Post savePost(PostSaveDto postSaveDto);
    Long updatePost(PostUpdateDto postUpdateDto);
    void deletePost(Long id);
}
