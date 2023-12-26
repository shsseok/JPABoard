package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserEmailDuplicateException;
import com.hyeon.jpaboard.exception.UserNotFoundException;
import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Long saveMember(MemberSaveDto memberSaveDto) {
       memberRepository.findByMemberEmail(memberSaveDto.getEmail())
               .ifPresent(member -> {throw new UserEmailDuplicateException("이메일이 중복 되었습니다");
               });
       Member member = memberSaveDto.toEntity(new Member());
       Member saveMember = memberRepository.save(member);
       return saveMember.getId();
    }

    @Override
    public Member findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException("해당하는 사용자가 없습니다."));
        return member;
    }
}
