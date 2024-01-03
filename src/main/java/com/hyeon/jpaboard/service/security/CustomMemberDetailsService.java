package com.hyeon.jpaboard.service.security;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String userEmail){
        Member member = memberRepository.findByMemberEmail(userEmail).orElseThrow(() -> new UserNotFoundException("해당하는 회원이 없습니다."));
        return new CustomMemberDetails(member);
    }
}
