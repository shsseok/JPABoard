package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.PostService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostListResponse;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceImplTest {
    @Autowired
    EntityManager em;
    @Autowired
    PostService postService;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    public void 게시물저장()  {
        //given
        MemberSaveDto memberSaveDto=new MemberSaveDto("ASD","심현석","1234");
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        PostSaveDto postSaveDto=new PostSaveDto(savedMemberId, "게시물 제목","게시물 내용");
        //when
        Post savedPost = postService.savePost(postSaveDto);
        em.flush();
        em.clear();

        Post findPost = postService.findPost(savedPost.getId());

        //then
        assertThat(savedPost).isEqualTo(findPost);
    }
    @Test
    public void 게시물단건조회()  {
        //given
        MemberSaveDto memberSaveDto=new MemberSaveDto("ASD","심현석","1234");
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        PostSaveDto postSaveDto=new PostSaveDto(savedMemberId, "게시물 제목","게시물 내용");
        //when
        Post savedPost = postService.savePost(postSaveDto);
        em.flush();
        em.clear();
        
        //when
        PostResponse postResponse = postService.findMemberNameWithPost(savedPost.getId());
        //then
        System.out.println("postResponse = " + postResponse.toString());
    }
    @Test
    @Rollback(value = false)
    public void 게시물전체조회() {
        //given
        MemberSaveDto memberSaveDto=new MemberSaveDto("ASD","심현석","1234");
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        PostSaveDto postSaveDto1=new PostSaveDto(savedMemberId, "게시물 제목1","게시물 내용1");
        PostSaveDto postSaveDto2=new PostSaveDto(savedMemberId, "게시물 제목2","게시물 내용2");
        //when
        Post savedPost1 = postService.savePost(postSaveDto1);
        Post savedPost2 = postService.savePost(postSaveDto2);
        em.flush();
        em.clear();
        
        //then  
        List<PostListResponse> postAll = postService.findPostAll();

        assertThat(postAll.size()).isEqualTo(2);
    }
    @Test
    @Rollback(value = false)
    public void 게시물수정(){
        //given
        MemberSaveDto memberSaveDto=new MemberSaveDto("ASD","심현석","1234");
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        PostSaveDto postSaveDto1=new PostSaveDto(savedMemberId, "게시물 제목1","게시물 내용1");
        PostSaveDto postSaveDto2=new PostSaveDto(savedMemberId, "게시물 제목2","게시물 내용2");
        //when
        Post savedPost1 = postService.savePost(postSaveDto1);
        Post savedPost2 = postService.savePost(postSaveDto2);
        em.flush();
        em.clear();
        
        //when
        PostUpdateDto postUpdateDto=new PostUpdateDto(savedPost1.getId(),"게시물을 수정함");
        Long updatePostId = postService.updatePost(postUpdateDto);
        //then 
        assertThat(savedPost1.getId()).isEqualTo(updatePostId);
        
    }
}