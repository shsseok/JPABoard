package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDto {

    @NotBlank(message = "이메일 입력은 필수 입니다.")
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$",message = "이메일 형식으로 입력해 주세요.")
    private String email;
    @NotEmpty(message = "이름은 필수로 입력하셔야 합니다.")
    private String username;
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    private String password;

}
