package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImpl implements PostService {
    @Override
    public Long savePost(PostSaveDto postSaveDto) {

    }

    @Override
    public Optional<Post> findPost(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Post> findPostAll() {
        return null;
    }

    @Override
    public List<Post> findPostByCreateAt(LocalDateTime createAt) {
        return null;
    }

    @Override
    public List<Post> findPostByTagsCount(Long tagCount) {
        return null;
    }

    @Override
    public List<Post> findByViewsCount(Long viewsCount) {
        return null;
    }

    @Override
    public Long updatePost(Long id) {
        return null;
    }

    @Override
    public void deletePost(Long id) {

    }
}
