package com.example.mimimimetr.mapper;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.CatRole;
import com.example.mimimimetr.model.CatState;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CatMapper {
    public static Cat toCatSignUp(SignUpForm signUpForm) {
        return Cat.builder()
                .name(signUpForm.getName())
                .email(signUpForm.getEmail())
                .avatar(signUpForm.getAvatar())
                .role(CatRole.USER)
                .state(CatState.CONFIRMED)
                .rateUser(0L)
                .build();
    }

    public static SignUpForm fromCat(Cat cat) {
        return SignUpForm.builder()
                .name(cat.getName())
                .email(cat.getEmail())
                .password(cat.getPassword())
                .build();
    }
}
