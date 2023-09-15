package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.repository.CatRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.mimimimetr.mapper.CatMapper.*;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepositoryJpa catRepositoryJpa;

    @Override
    public SignUpForm signUpUser(SignUpForm signUpForm) {
        return fromUser(catRepositoryJpa.save(toCat(signUpForm)));
    }
}
