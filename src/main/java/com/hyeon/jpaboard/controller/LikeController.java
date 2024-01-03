package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.service.LikesService;
import com.hyeon.jpaboard.service.security.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {
    private final LikesService likesService;
    @GetMapping("/{postId}")
    public String postLike(@PathVariable("postId") Long postId, @AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {
        likesService.choiceLike(postId,customMemberDetails.getUsername());
        return "redirect:/post/"+postId;
    }
    @GetMapping("/cancel/{postId}")
    public String postTagCancel(@PathVariable("postId") Long postId,@AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {
        likesService.deleteLike(postId,customMemberDetails.getUsername());
        return "redirect:/post/"+postId;
    }


}
