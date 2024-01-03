package com.hyeon.jpaboard.service.serviceImpl.dto.request;

import com.hyeon.jpaboard.domain.Post;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDto {
    @NotEmpty(message = "내용을 입력하세요.")
    private String postContent;
}
