package com.hyeon.jpaboard.service;




public interface TagService {
    void choiceTag(Long postId,String memberEmail);
    void deleteTag(Long postId,String memberEmail);
}
