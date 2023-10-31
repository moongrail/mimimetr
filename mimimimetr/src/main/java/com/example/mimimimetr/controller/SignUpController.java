package com.example.mimimimetr.controller;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.service.auth.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
@Slf4j
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }

        log.info("Starting 'get /signUp'");
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm signUpForm, Errors errors, Model model) {
        log.info("Starting 'post /signUp'");
        if (errors.hasErrors()) {
            log.error("Validation error");
            log.error(errors.getAllErrors().toString());
            model.addAttribute("signUpForm", signUpForm);
            return "signUp";
        }

        signUpService.signUp(signUpForm);
        return "redirect:/login";
    }
}
