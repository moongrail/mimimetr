package com.example.mimimimetr.controller;

import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.service.game.CatGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final CatGameService catGameService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String playGame( Model model) {

        List<CatGameDto> catPairs = catGameService.getCatListForGame(1L);

        model.addAttribute("catPairs", catPairs);

        return "game";
    }


    @PostMapping()
    public String submitVote(@RequestParam("votedCatId") Long votedCatId) {
//        Long currentUserId = getCurrentUserId();

        catGameService.addLike(votedCatId);

        return "redirect:/game/";
    }

}
