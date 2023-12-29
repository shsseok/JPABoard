package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.service.PostService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/postlist")
    public String postList(Model model)
    {
        List<PostListResponse> postList = postService.findPostAll();
        model.addAttribute("postList",postList);
        return "post/postList";
    }
    @GetMapping("/add")
    public String postForm(Model model)
    {
        model.addAttribute("postForm",new PostSaveDto());
        return "post/postForm";
    }
    @PostMapping("/add")
    public String postForm(@Valid @ModelAttribute("postForm") PostSaveDto postSaveDto , BindingResult bindingResult)
    {
       if(bindingResult.hasErrors())
       {
           return "redirect:/post/add";
       }
        postService.savePost(postSaveDto);
        return "redirect:/post/postlist";
    }

}

