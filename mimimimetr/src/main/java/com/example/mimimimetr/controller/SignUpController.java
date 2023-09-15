package com.example.mimimimetr.controller;


import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.exception.UniqueUsernameException;
import com.example.mimimimetr.service.CatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signUp")
@Slf4j
@RequiredArgsConstructor
public class SignUpController {
    private final CatService catService;

    @GetMapping
    public String getSignUpPage(Model model) {
        log.info("Starting 'get /signUp'");
        model.addAttribute("signUpForm", new SignUpForm());
        return "SignUp";
    }

    @PostMapping
    public String signUpUser(@Valid SignUpForm form, BindingResult bindingResult, Model model) {
        log.info("Starting 'post /signUp': post 'form' - {}, 'bindingResult' - {}"
                , form.toString(), bindingResult.toString());
        if (bindingResult.hasErrors()) {
            log.error("Can't create new user, 'bindingResult' has error(s) - {}", bindingResult.getAllErrors());
            model.addAttribute("signUpForm", form);
            return "SignUp";
        }

        try {
            catService.signUpUser(form);
            return "redirect:/login";
        } catch (UniqueUsernameException e) {
            log.info("Caught UniqueUsernameException exception");

            model.addAttribute("signUpForm", form);
            model.addAttribute("emailAlreadyExists", Boolean.TRUE);

            return "SignUp";
        }
    }

}
