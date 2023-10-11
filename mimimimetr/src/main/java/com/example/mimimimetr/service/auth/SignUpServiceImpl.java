package com.example.mimimimetr.service.auth;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.exception.UniqueEmailCatException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.repository.CatRepositoryJpa;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.mimimimetr.mapper.CatMapper.toCatSignUp;

@Service
@AllArgsConstructor
@Slf4j
public class SignUpServiceImpl implements SignUpService {
    private final CatRepositoryJpa catRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        if (catRepositoryJpa.existsByEmail(signUpForm.getEmail())) {
            log.error("Email already exists");
            throw new UniqueEmailCatException("Email already exists");
        }

        Cat newCat = toCatSignUp(signUpForm);
        newCat.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        Cat save = catRepositoryJpa.save(newCat);
        log.info("New cat: {}", save);
    }
}
