package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Likes;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.exception.LikeDuplicateException;
import com.hyeon.jpaboard.exception.LikeNotFoundExcption;
import com.hyeon.jpaboard.exception.PostNotFoundException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.LikeRepository;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import com.hyeon.jpaboard.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeServiceImpl implements LikesService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    @Override
    @Transactional
    public void choiceLike(Long postId,String memberEamil) {

        Member member = memberCheck(memberEamil);
        Post post = postCheck(postId);
        Likes like = likeCheck(member, post);
        if(post.getMember().getMemberEmail()==memberEamil)
        {
            throw new LikeDuplicateException("본인의 게시물은 좋아요를 할 수 없습니다.");
        }
        if (like!=null)
        {
            throw new LikeDuplicateException("이미 좋아요를 하였습니다.");
        }
        Likes likes = Likes.createLikes(member);
        likes.setPost(post);
        likeRepository.save(likes);

    }

    @Override
    @Transactional
    public void deleteLike(Long postId,String memberEamil) {
        Member member = memberCheck(memberEamil);
        Post post = postCheck(postId);
        Likes like = likeCheck(member, post);
        if (like==null)
        {
            throw new LikeNotFoundExcption("좋아요를 하지 않았습니다.");
        }
        likeRepository.delete(like);

    }

    public Likes likeCheck(Member member,Post post)
    {
         return likeRepository.findLikeByMemberAndPost(member, post).orElse(null);
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
