package com.example.mimimimetr.mapper;

import com.example.mimimimetr.dto.CatDto;
import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.Role;
import com.example.mimimimetr.model.State;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@UtilityClass
public class CatMapper {
    public static Cat toCatSignUp(SignUpForm signUpForm) {
        return Cat.builder()
                .name(signUpForm.getName())
                .email(signUpForm.getEmail())
                .role(Role.USER)
                .state(State.CONFIRMED)
                .rateCat(0L)
                .build();
    }

    public static SignUpForm fromCat(Cat cat) {
        return SignUpForm.builder()
                .name(cat.getName())
                .email(cat.getEmail())
                .password(cat.getPassword())
                .build();
    }

    public static CatDto catDtoFromCat(Cat cat) {
        return CatDto.builder()
                .id(cat.getId())
                .name(cat.getName())
                .rateCat(cat.getRateCat())
                .build();
    }

    public static List<CatDto> catDtoListFromCats(List<Cat> cats) {
        return cats.stream()
                .map(CatMapper::catDtoFromCat)
                .collect(Collectors.toList());
    }

    public static CatGameDto catGameDtoFromCat(Cat cat) {
        return CatGameDto.builder()
                .id(cat.getId())
                .name(cat.getName())
                .build();
    }

    public static Queue<CatGameDto> catGameDtoListFromCats(List<Cat> cats) {
        return cats.stream()
                .map(CatMapper::catGameDtoFromCat)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
