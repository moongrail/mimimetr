package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.dto.UserSignUpDto;
import org.springframework.stereotype.Service;

public interface UserService {
    SignUpForm signUpUser(SignUpForm signUpForm);
}
