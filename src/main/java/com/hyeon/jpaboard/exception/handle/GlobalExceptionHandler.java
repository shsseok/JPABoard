package com.hyeon.jpaboard.exception.handle;

import com.hyeon.jpaboard.exception.ResponseStatusException;
import com.hyeon.jpaboard.exception.TagDuplicateException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TagDuplicateException.class)
    public String handleTagDuplicateException(Model model,TagDuplicateException ex)
    {
        Long postId = ex.getPostId();
        model.addAttribute("exception",ex.getMessage());
        return "error/500error";
    }
    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(Model model,DataAccessException e) {
        model.addAttribute("exception","데이터베이스 접근 관련 에러 입니다.");
        return "error/500error";
    }
    @ExceptionHandler(ResponseStatusException.class)
    public String handlePostAuthorization(Model model,ResponseStatusException e) {
        model.addAttribute("exception",e.getMessage());
        return "error/500error";
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404error(Model model,NoHandlerFoundException e) {
        model.addAttribute("exception",e.getMessage());
        return "error/404error";
    }

}
