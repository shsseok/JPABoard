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
    public void choiceTag(Long postId,String memberEmail) {
        Member member = memberCheck(memberEmail);
        Post post = postCheck(postId);
        Tag findTag = tagCheck(member, post);
        if(post.getMember().getMemberEmail()==memberEmail)
        {
            throw new TagDuplicateException("본인 게시물은 태그 할 수 없습니다.",postId);
        }
        if(findTag!=null)
        {
            throw new TagDuplicateException("이미 게시물을 태그하고 있습니다",postId);
        }
        Tag tag = Tag.createTag(member, post);
        tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteTag(Long postId,String memberEmail) {
        Member member = memberCheck(memberEmail);
        Post post = postCheck(postId);
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
    public Member memberCheck(String memberEmail)
    {
        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(() -> new UserNotFoundException("해당하는 유저가 없습니다."));
        return member;
    }
    public Post postCheck(Long postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("해당하는 게시물이 없습니다."));
        return post;
    }
}
