package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.repository.MemberRepository;
import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.TagService;
import com.hyeon.jpaboard.service.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @GetMapping("/{postId}")
    public String postTag(@PathVariable("postId") Long postId,@AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {
        tagService.choiceTag(postId,customMemberDetails.getUsername());
        return "redirect:/post/"+postId;
    }
    @GetMapping("/cancel/{postId}")
    public String postTagCancel(@PathVariable("postId") Long postId,@AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {
        tagService.deleteTag(postId,customMemberDetails.getUsername());
        return "redirect:/post/"+postId;
    }
}
