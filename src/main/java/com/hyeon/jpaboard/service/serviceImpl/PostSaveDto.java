package com.hyeon.jpaboard.service.serviceImpl;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSaveDto {
    private Long userId;
    private String postTitle;
    private String postContent;
}
