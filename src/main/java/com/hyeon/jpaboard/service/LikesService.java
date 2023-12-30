package com.hyeon.jpaboard.service;

import com.hyeon.jpaboard.service.serviceImpl.dto.request.LikeDto;

public interface LikesService {
    void choiceLike(LikeDto likeDto);
    void deleteLike(LikeDto likeDto);
}
