package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Queue;

import static com.example.mimimimetr.mapper.CatMapper.catGameDtoListFromCats;

@Service
@RequiredArgsConstructor
public class CatGameServiceImpl implements CatGameService {
    private final CatRepository catRepository;
    @Override
    public void addLike(Long catId) {
        Cat catForLike = catRepository.findById(catId)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        catForLike.setRateCat(catForLike.getRateCat() + 1);
        catRepository.save(catForLike);
    }

    @Override
    public Queue<CatGameDto> getCatListForGame(Long catId) {
        List<Cat> allByIdIsNot = catRepository.findAllByIdIsNot(catId);
        Collections.shuffle(allByIdIsNot);

        return catGameDtoListFromCats(allByIdIsNot);
    }


}
