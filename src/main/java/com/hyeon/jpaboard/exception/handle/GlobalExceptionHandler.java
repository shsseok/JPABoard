package com.hyeon.jpaboard.exception.handle;

import com.hyeon.jpaboard.exception.TagDuplicateException;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TagDuplicateException.class)
    public String handleTagDuplicateException(Model model,TagDuplicateException ex)
    {
        Long postId = ex.getPostId();
        String errorMessage = URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
        return "redirect:/post/"+postId;
    }
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();
        return "error/serverError";
    }
    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(DataAccessException e) {
        e.printStackTrace();
        return "error/databaseError";
    }
}
