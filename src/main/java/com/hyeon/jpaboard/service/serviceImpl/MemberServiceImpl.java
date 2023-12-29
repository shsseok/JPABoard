package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Authority;
import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserEmailDuplicateException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.token.UserDto;
import com.hyeon.jpaboard.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public Long saveMember(MemberSaveDto memberSaveDto) {
       memberRepository.findByMemberEmail(memberSaveDto.getEmail())
               .ifPresent(member -> {throw new UserEmailDuplicateException("이메일이 중복 되었습니다");
               });
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        Member member = Member.builder()
                .memberName(memberSaveDto.getUsername())
                .memberEmail(memberSaveDto.getEmail())
                .memberPassword(bCryptPasswordEncoder.encode(memberSaveDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .build();
        Member saveMember = memberRepository.save(member);
       return saveMember.getId();
    }

    @Override
    public Member findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당하는 사용자가 없습니다."));
        return member;
    }
    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String memberEamil) {
        return UserDto.from(memberRepository.findOneWithAuthoritiesByMemberEmail(memberEamil).orElse(null));
    }
    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities() {
        return UserDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(memberRepository::findOneWithAuthoritiesByMemberEmail)
                        .orElseThrow(() -> new UserNotFoundException("Member not found"))
        );
    }
}
