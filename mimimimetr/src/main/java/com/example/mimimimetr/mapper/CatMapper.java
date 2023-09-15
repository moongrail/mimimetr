package com.example.mimimimetr.mapper;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.model.Cat;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CatMapper {
    public static Cat toCat(SignUpForm signUpForm) {
        return Cat.builder()
                .name(signUpForm.getName())
                .password(signUpForm.getPassword())
                .image(signUpForm.getImage())
                .build();
    }
    public static SignUpForm fromUser(Cat cat) {
        return SignUpForm.builder()
                .name(cat.getName())
                .password(cat.getPassword())
                .image(cat.getImage())
                .build();
    }
}
