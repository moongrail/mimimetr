package com.example.mimimimetr.controller;

import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.service.CatGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.Queue;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final CatGameService catGameService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{catId}")
    public String playGame(@PathVariable Long catId, Model model) {

        Queue<CatGameDto> catPairs = catGameService.getCatListForGame(catId);

        if (catPairs.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("catPairs", catPairs);

        return "game";
    }

    @PostMapping("/{catId}")
    public String submitVote(@PathVariable Long catId, @RequestParam("votedCatId") Long votedCatId) {
//        Long currentUserId = getCurrentUserId();

        catGameService.addLike(votedCatId);

        return "redirect:/game/" + catId;
    }
}
