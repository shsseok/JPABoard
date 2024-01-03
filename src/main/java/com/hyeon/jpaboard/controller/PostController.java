package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.domain.Post;
import com.hyeon.jpaboard.exception.ResponseStatusException;
import com.hyeon.jpaboard.service.PostService;
import com.hyeon.jpaboard.service.security.CustomMemberDetails;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.PostUpdateDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/postlist")
    public String postList(Model model)
    {
        List<PostResponse> postList = postService.findPostAll();
        model.addAttribute("postList",postList);
        return "post/postList";
    }
    @GetMapping("/add")
    public String postForm(Model model)
    {
        model.addAttribute("postForm",new PostSaveDto());
        return "post/postForm";
    }
    @GetMapping("/{postId}")
    public String postDetailForm(@PathVariable("postId") Long postId,Model model
            ,@AuthenticationPrincipal CustomMemberDetails customMemberDetails
                ,@RequestParam(required = false, name = "isView") String isView)
    {
        if("ok".equals(isView))
        {
            postService.updateView(postId);
        }
        PostResponse postResponse = postService.findMemberNameWithPost(postId,customMemberDetails.getUsername());
        model.addAttribute("postResponse",postResponse);
        return "post/DetailPostForm";
    }
    @GetMapping("/edit/{postId}")
    public String updatePostForm(@PathVariable("postId") Long postId,
                                @AuthenticationPrincipal CustomMemberDetails customMemberDetails,
                                 Model model)
    {
        //권한 체크
        Post post = postService.findPost(postId);
        if(post.getMember().getMemberEmail()!= customMemberDetails.getUsername()){
            throw new ResponseStatusException("수정 권한이 없습니다.");
        }
        PostUpdateDto postUpdateDto=new PostUpdateDto(post.getPostContent());
        model.addAttribute("postUpdateDto",postUpdateDto);
        return "post/UpdatePostForm";
    }
    @PostMapping("/edit/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @Valid @ModelAttribute("postUpdateDto") PostUpdateDto postUpdateDto , BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "post/UpdatePostForm";
        }

        postService.updatePost(postUpdateDto,postId);
        return "redirect:/post/postlist";
    }
    @GetMapping ("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId,@AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {   Post post = postService.findPost(postId);
        if(post.getMember().getMemberEmail()!= customMemberDetails.getUsername()){
            throw new ResponseStatusException("삭제 권한이 없습니다.");
        }
        postService.deletePost(postId);
        return "redirect:/post/postlist";
    }
    @PostMapping("/add")
    public String postForm(@AuthenticationPrincipal CustomMemberDetails customMemberDetails, @Valid @ModelAttribute("postForm") PostSaveDto postSaveDto , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/post/add";
        }
        if (customMemberDetails!=null)
        {
            postService.savePost(postSaveDto,customMemberDetails.getUsername());
        }

        return "redirect:/post/postlist";
    }
/*    @PostMapping("/postlist/search")
    public String postForm()
    {

    }*/

}