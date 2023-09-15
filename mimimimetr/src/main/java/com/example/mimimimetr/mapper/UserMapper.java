package com.example.mimimimetr.mapper;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public static User toUser(SignUpForm signUpForm) {
        return User.builder()
                .username(signUpForm.getUsername())
                .password(signUpForm.getPassword())
                .build();
    }
    public static SignUpForm fromUser(User user) {
        return SignUpForm.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
