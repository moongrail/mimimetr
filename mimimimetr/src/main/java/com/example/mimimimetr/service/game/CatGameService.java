package com.example.mimimimetr.service.game;

import com.example.mimimimetr.dto.CatGameDto;

import java.util.List;

public interface CatGameService {
    void addLike(String emailCurrentUser, Long votedCatId);

    List<CatGameDto> getCatListForGame(String email);
}
