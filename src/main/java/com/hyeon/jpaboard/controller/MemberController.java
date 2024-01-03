package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.service.MemberService;
import com.hyeon.jpaboard.service.serviceImpl.dto.request.MemberSaveDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register-form")
    public String memberForm(Model model)
    {
        model.addAttribute("memberForm",new MemberSaveDto());
        return "members/memberForm";
    }
    @PostMapping("/register")
    public String memberRegister(@Valid @ModelAttribute("memberForm")  MemberSaveDto memberSaveDto , BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "members/memberForm";
        }
        Long savedMemberId = memberService.saveMember(memberSaveDto);
        return "redirect:/";
    }

}
