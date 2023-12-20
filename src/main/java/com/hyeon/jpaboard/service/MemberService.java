package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.service.serviceImpl.MemberSaveDto;

public interface MemberService {
    void saveMemer(MemberSaveDto memberSaveDto);
    Member findMember(Long id);

}
