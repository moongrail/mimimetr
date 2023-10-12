package com.example.mimimimetr.service.auth;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.exception.UniqueEmailCatException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.CatImage;
import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.repositories.CatImageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.mimimimetr.mapper.CatMapper.toCatSignUp;

@Service
@AllArgsConstructor
@Slf4j
public class SignUpServiceImpl implements SignUpService {
    private final CatRepository catRepository;
    private final CatImageRepository catImageRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        if (catRepository.existsByEmail(signUpForm.getEmail())) {
            log.error("Email already exists");
            throw new UniqueEmailCatException("Email already exists");
        }

        Cat newCat = toCatSignUp(signUpForm);
        newCat.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Cat save = catRepository.save(newCat);
        try {
            catImageRepository.save(CatImage.builder()
                    .cat(save)
                    .content(signUpForm.getAvatar().getBytes())
                    .build());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("New cat: {}", save);
    }
}
