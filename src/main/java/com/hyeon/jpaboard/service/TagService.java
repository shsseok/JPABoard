package com.hyeon.jpaboard.service;


import com.hyeon.jpaboard.service.serviceImpl.dto.request.TagDto;

public interface TagService {
    void choiceTag(TagDto tagDto);
    void deleteTag(TagDto tagDto);
}
