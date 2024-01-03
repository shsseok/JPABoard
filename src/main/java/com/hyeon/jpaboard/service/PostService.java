package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    Post findPost(Long id);
    PostResponse findMemberNameWithPost(Long id,String memberEmail);
    List<PostResponse> findPostAll();
    Post savePost(PostSaveDto postSaveDto,String memberEmail);
    Long updatePost(PostUpdateDto postUpdateDto,Long postId);

    void updateView(Long postId);
    void deletePost(Long id);
}
