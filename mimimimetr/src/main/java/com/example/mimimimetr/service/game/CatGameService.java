package com.example.mimimimetr.service.game;

import com.example.mimimimetr.dto.CatDto;
import com.example.mimimimetr.dto.CatGameDto;

import java.util.List;
import java.util.Queue;

public interface CatGameService {
    void addLike(Long catId);

    Queue<CatGameDto> getCatListForGame(Long catId);

}
