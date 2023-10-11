package com.example.mimimimetr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
@Slf4j
public class SignInController {
    @GetMapping
    public String getSignInPage(Authentication auth) {
        if (auth != null) {
            return "redirect:/";
        }
        return "signIn";
    }
}
