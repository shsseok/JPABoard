package com.hyeon.jpaboard.service.serviceImpl.security;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserServiceDetails implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}


