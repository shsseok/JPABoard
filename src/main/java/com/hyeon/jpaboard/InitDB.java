package com.hyeon.jpaboard;

import com.hyeon.jpaboard.domain.Member;

import com.hyeon.jpaboard.domain.Post;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        }


    }

}
