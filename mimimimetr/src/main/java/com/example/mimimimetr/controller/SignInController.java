package com.example.mimimimetr.controller;

import com.example.mimimimetr.dto.SignInForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class SignInController {
    @GetMapping
    public String getSignInPage(Authentication auth,Model model) {
        if (auth != null) {
            return "redirect:/";
        }
        model.addAttribute("signInForm", new SignInForm());
        log.info("Starting 'get /login'");
        return "signIn";
    }
}
