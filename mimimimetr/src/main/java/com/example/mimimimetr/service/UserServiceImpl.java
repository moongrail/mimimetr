package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.dto.UserSignUpDto;
import com.example.mimimimetr.mapper.UserMapper;
import com.example.mimimimetr.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.mimimimetr.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryJpa userRepository;

    @Override
    public SignUpForm signUpUser(SignUpForm signUpForm) {
        return fromUser(userRepository.save(toUser(signUpForm)));
    }
}
