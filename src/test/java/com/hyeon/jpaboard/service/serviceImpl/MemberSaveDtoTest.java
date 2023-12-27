package com.hyeon.jpaboard.service.serviceImpl;


import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import jakarta.validation.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberSaveDtoTest {

    @Autowired
    private Validator validator;

    @Test
    @DisplayName("이메일 형식이 올바른지 테스트")
    public void 이메일형식이올바름테스트() {
        MemberSaveDto dto = new MemberSaveDto("validemail@example.com","JohnDoe","pass123");
        // 유효성 검사 수행
        var violations = validator.validate(dto);
        // 유효성 검사 결과 확인
        assertTrue(violations.isEmpty(), "유효한 이메일이어야 함");
    }

    @Test
    @DisplayName("이메일 형식이 올바르지 않을 때 테스트")
    public void 이메일형식이올바르지않음테스트() {
        MemberSaveDto dto = new MemberSaveDto("invalid_email_example.com","JohnDoe","pass123");
        // 유효성 검사 수행
        var violations = validator.validate(dto);

        // 유효성 검사 결과 확인
        assertFalse(violations.isEmpty(), "유효하지 않은 이메일 형식이어야 함");
    }
}
