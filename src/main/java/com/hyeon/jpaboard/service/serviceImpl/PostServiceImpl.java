package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import com.hyeon.jpaboard.service.PostService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostListResponse;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    @Override
    @Transactional
    public Post savePost(PostSaveDto postSaveDto) {;
        Member findMember = memberRepository.findById(postSaveDto.getMemberId()).orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
        Post post = PostSaveDto.toEntity(postSaveDto, findMember);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }
    @Override
    public PostResponse findMemberNameWithPost(Long id) {
        return postRepository.findById(id)
                .map(post -> PostResponse.toDto(post)
                )
                .orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
    }
    @Override
    public Post findPost(Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return post;
    }
    @Override
    public List<PostListResponse> findPostAll()
    {
        return postRepository.findAll().stream()
                .map(post -> PostListResponse.toDto(post))
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public Long updatePost(PostUpdateDto postUpdateDto)
    {
        Post updatePost = postRepository.findById(postUpdateDto.getPostId())
                .map(post -> post.updatePost(postUpdateDto))
                .orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return  updatePost.getId();
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post deletePost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        postRepository.delete(deletePost);
    }
}
