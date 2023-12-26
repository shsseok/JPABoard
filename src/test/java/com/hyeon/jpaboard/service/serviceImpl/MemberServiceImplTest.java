package com.hyeon.jpaboard.service.serviceImpl;

import com.hyeon.jpaboard.domain.Member;
import com.hyeon.jpaboard.exception.UserEmailDuplicateException;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceImplTest {
    @Autowired
    MemberService memberService;
    @Test
    @DisplayName("회원가입")
    public void 회원가입() {
        //given
        MemberSaveDto memberSaveDto=new MemberSaveDto("qwe123@naver.com","dd","1234");
        //when;
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        //then
        Member member = memberService.findMember(savedMemberId);
        assertThat(savedMemberId).isEqualTo(member.getId());
    }
    @Test
    @DisplayName("이메일 중복확인")
    public void 회원이메일중복확인() {
        //given
        MemberSaveDto memberSaveDto1=new MemberSaveDto("ddd","dd","1234");
        MemberSaveDto memberSaveDto2=new MemberSaveDto("ddd","홍길동","1234");
        //then
        Long savedMemberId = memberService.saveMember(memberSaveDto1);
        assertThrows(
               UserEmailDuplicateException.class, ()
               ->
               {
                   memberService.saveMember(memberSaveDto2);
               }
               ,"예외가 발생하지 않음(테스트실패)"
       );

    }



}