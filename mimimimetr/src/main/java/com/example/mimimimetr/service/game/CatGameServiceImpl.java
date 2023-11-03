package com.example.mimimimetr.service.game;

import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.Like;
import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.repositories.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.example.mimimimetr.mapper.CatMapper.catGameDtoListFromCats;

@Service
@RequiredArgsConstructor
public class CatGameServiceImpl implements CatGameService {

    public static final int LIMIT_CAT_ON_PAGE = 2;
    private final CatRepository catRepository;
    private final LikesRepository likesRepository;

    @Override
    public void addLike(String emailCurrentUser, Long votedCatId) {
        Cat currentCat = catRepository.findByEmail(emailCurrentUser)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));
        Cat votedCat = catRepository.findById(votedCatId)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found"));

        if (!hasLikedCat(currentCat, votedCat)) {
            Like like = Like.builder()
                    .cat(currentCat)
                    .votedCat(votedCat)
                    .build();
            likesRepository.save(like);

            votedCat.setRateCat(votedCat.getRateCat() + 1);
            votedCat.getLikes().add(like);
            catRepository.save(votedCat);
        }
    }

    private boolean hasLikedCat(Cat currentCat, Cat votedCat) {
        return currentCat.getLikes().stream()
                .anyMatch(like -> like.getVotedCat().equals(votedCat));
    }

    @Override
    public List<CatGameDto> getCatListForGame(String email) {
        List<Cat> catsWithoutLike = catRepository
                .findAllByEmailIsNotAndLikesNotContainingRandom(email, LIMIT_CAT_ON_PAGE);
        Collections.shuffle(catsWithoutLike);

        return catGameDtoListFromCats(catsWithoutLike);
    }
}
