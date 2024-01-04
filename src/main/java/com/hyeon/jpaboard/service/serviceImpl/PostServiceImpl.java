package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.*;
import com.hyeon.jpaboard.service.PostService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final LikeRepository likeRepository;
    private final PostFilterRepository postFilterRepository;

    @Override
    @Transactional
    public Post savePost(PostSaveDto postSaveDto, String memberEmail) {
        ;
        Member findMember = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
        Post post = PostSaveDto.toEntity(postSaveDto, findMember);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }

    @Override
    public PostResponse findMemberNameWithPost(Long id, String memberEmail) {
        Member findMember = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
        Boolean isTag = tagRepository.existsByPostIdAndMember(id, findMember);
        Boolean isLike = likeRepository.existsLikesByPostIdAndMember(id, findMember);
        return postRepository.findById(id)
                .map(post -> PostResponse.toDto(post)
                )
                .map(postResponse -> postResponse.setTag(isTag))
                .map(postResponse -> postResponse.setLike(isLike))
                .orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
    }

    @Override
    public Post findPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return post;
    }

    @Override
    public List<PostResponse> findPostAll() {
        return postRepository.findAllWithMembers().stream()
                .peek(post -> log.info("null={}", post.getPostContent()))
                .map(PostResponse::toDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Long updatePost(PostUpdateDto postUpdateDto, Long postId) {

        Post updatePost = postRepository.findById(postId)
                .map(post -> post.updatePost(postUpdateDto))
                .orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return updatePost.getId();
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        Post deletePost = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        postRepository.delete(deletePost);
    }

    //조회수 로직
    @Override
    @Transactional
    public void updateView(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        post.upPostView(post);
    }
    @Override
    public List<PostResponse> findSortPostList(String sortMethod) {
       return postFilterRepository.findSortAllList(sortMethod).stream()
                .map(PostResponse::toDto)
                .collect(Collectors.toList());
    }
}
