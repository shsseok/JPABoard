package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.domain.Tag;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.TagDuplicateException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import com.hyeon.jpaboard.repository.TagRepository;
import com.hyeon.jpaboard.service.TagService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    @Override
    @Transactional
    public void choiceTag(TagDto tagDto) {
        Member member = memberCheck(tagDto.getMemberId());
        Post post = postCheck(tagDto.getPostId());
        Tag tag = tagCheck(member, post);
        if(tag!=null)
        {
            throw new TagDuplicateException("이미 게시물을 태그하고 있습니다");
        }
        tagRepository.save(TagDto.toEntity(member,post));
    }

    @Override
    @Transactional
    public void deleteTag(TagDto tagDto) {
        Member member = memberCheck(tagDto.getMemberId());
        Post post = postCheck(tagDto.getPostId());
        Tag tag = tagCheck(member, post);
        if(tag==null)
        {
            throw new TagDuplicateException("취소할 태그가 없습니다.");
        }
        tagRepository.delete(tag);
    }
    public Tag tagCheck(Member member, Post post)
    {
        return tagRepository.findByMemberAndPost(member,post).orElse(null);
    }
    public Member memberCheck(Long memberId)
    {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
        return member;
    }
    public Post postCheck(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return post;
    }
}
