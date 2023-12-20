package com.hyeon.jpaboard.service.serviceImpl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberSaveDto {

    @NotBlank(message = "이메일 입력은 필수 입니다.")
    @Email(message = "이메일 형식으로 입력해 주세요.")
    private String email;
    @NotEmpty(message = "이름은 필수로 입력하셔야 합니다.")
    private String username;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String password;
}
