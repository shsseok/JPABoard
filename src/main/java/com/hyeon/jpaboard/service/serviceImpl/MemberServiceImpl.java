package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserEmailDuplicateException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public void saveMemer(MemberSaveDto memberSaveDto) {
        memberRepository.findByMemberEmail(memberSaveDto.getEmail()).orElseThrow(() -> new UserEmailDuplicateException("이메일이 중복되었습니다."));
        Member member=Member.builder()
                .memberEmail(memberSaveDto.getEmail())
                .memberPassword(memberSaveDto.getPassword())
                .memberName(memberSaveDto.getUsername())
                        .build();
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당하는 사용자가 없습니다."));
        return member;
    }
}
