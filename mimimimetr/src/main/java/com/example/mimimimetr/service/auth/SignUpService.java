package com.example.mimimimetr.service.auth;

import com.example.mimimimetr.dto.SignUpForm;
import org.springframework.web.multipart.MultipartFile;

public interface SignUpService {
    void signUp(SignUpForm signUpForm);
}
