package com.hyeon.jpaboard;

import com.hyeon.jpaboard.domain.Member;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init()
    {
        initService.memberInit();
    }
    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService
    {
        private final EntityManager em;
        private final MemberRepository memberRepository;
        private final PostRepository postRepository;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        public void memberInit()
        {
            Member member1=Member.builder()
                    .memberEmail("tlatms8619@naver.com")
                    .memberPassword(bCryptPasswordEncoder.encode("1234"))
                    .role("ROLE_USER")
                    .memberName("심현석")
                    .build();
            Member member2=Member.builder()
                    .memberEmail("123@naver.com")
                    .memberPassword(bCryptPasswordEncoder.encode("1234"))
                    .role("ROLE_USER")
                    .memberName("홍길동")
                    .build();

            em.persist(member1);
            em.persist(member2);
            em.flush();
            em.clear();

            Post post1=Post.builder()
                    .postContent("심현석 글 제목1")
                    .postTitle("심현석님이 쓴 글1")
                    .member(member1)
                    .localDateTime(LocalDateTime.of(2023,12,1,1,1))
                    .build();
            Post post2=Post.builder()
                    .postContent("심현석 글 제목2")
                    .postTitle("심현석님이 쓴 글2")
                    .member(member1)
                    .localDateTime(LocalDateTime.of(2023,12,1,1,2))
                    .build();
            Post post3=Post.builder()
                    .postContent("홍길동 글 제목1")
                    .postTitle("홍길동님이 쓴 글1")
                    .member(member2)
                    .localDateTime(LocalDateTime.of(2023,12,1,1,3))
                    .build();
            Post post4=Post.builder()
                    .postContent("홍길동 글 제목2")
                    .postTitle("홍길동님이 쓴 글2")
                    .member(member2)
                    .localDateTime(LocalDateTime.of(2023,12,1,1,4))
                    .build();
            em.persist(post1);
            em.persist(post2);
            em.persist(post3);
            em.persist(post4);
            em.flush();
            em.clear();

        }


    }

}
