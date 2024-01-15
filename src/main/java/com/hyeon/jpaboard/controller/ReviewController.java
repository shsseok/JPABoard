package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.exception.ReviewNotFoundException;
import com.hyeon.jpaboard.service.ReviewService;
import com.hyeon.jpaboard.service.security.CustomMemberDetails;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.ReviewSaveDto;
import com.hyeon.jpaboard.service.serviceImpl.dto.response.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("{postId}/add")
    public String reviewForm(Model model, @PathVariable("postId") Long postId) {
        model.addAttribute("reviewSaveDto", new ReviewSaveDto());
        model.addAttribute("postId", postId);
        return "review/reviewForm";
    }

    @PostMapping("{postId}/add")
    public String reviewForm(@PathVariable("postId") Long postId, @Valid @ModelAttribute("reviewSaveDto") ReviewSaveDto reviewSaveDto, BindingResult bindingResult, @AuthenticationPrincipal CustomMemberDetails customMemberDetails) {

        if (bindingResult.hasErrors()) {
            return "/review/reviewForm";
        }
        if (reviewSaveDto.getParentId() != null) {
            throw new ReviewNotFoundException("해당 하는 리뷰를 찾을 수 없습니다");
        }
        reviewService.saveReview(reviewSaveDto, postId, customMemberDetails.getUsername());
        return "redirect:/review/" + postId + "/reviewlist";
    }

    @GetMapping("{postId}/reviewlist")
    @ResponseBody
    public List<ReviewResponse> reviewList(Model model, @PathVariable("postId") Long postId) {
        List<ReviewResponse> reviewLists = reviewService.findReviewLists(postId);

        return reviewLists;
    }

    @PostMapping("{postId}/child/add")
    public String writeChildReview(@Valid ReviewSaveDto reviewSaveDto,
                                   BindingResult bindingResult,
                                   @PathVariable("postId") Long postId,
                                   @AuthenticationPrincipal CustomMemberDetails customMemberDetails) {
        if (bindingResult.hasErrors()) {
            return "/review/"+postId+"/reviewlist";
        }
        if (reviewSaveDto.getParentId() == null) {
            throw new ReviewNotFoundException("해당 하는 댓글을 찾을 수 없습니다");
        }
        reviewService.saveReview(reviewSaveDto, postId, customMemberDetails.getUsername());
        return "/review/reviewList";
    }


}
