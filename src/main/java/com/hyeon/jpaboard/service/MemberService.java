package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;

public interface MemberService {
    Long saveMember(MemberSaveDto memberSaveDto);
    Member findMember(Long id);

}
