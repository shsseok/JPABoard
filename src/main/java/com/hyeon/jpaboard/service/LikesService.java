package com.hyeon.jpaboard.service;


public interface LikesService {
    void choiceLike(Long postId,String memberEamil);
    void deleteLike(Long postId,String memberEamil);
}
