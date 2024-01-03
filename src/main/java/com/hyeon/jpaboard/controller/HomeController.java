package com.hyeon.jpaboard.controller;

import com.hyeon.jpaboard.service.security.CustomMemberDetails;
import lombok.Getter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model,@AuthenticationPrincipal CustomMemberDetails customMemberDetails)
    {
        if(customMemberDetails !=null)
        {
            model.addAttribute("userEmail",customMemberDetails.getUsername());
        }
        return "home";
    }

}
