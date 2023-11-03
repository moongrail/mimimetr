package com.example.mimimimetr.controller;

import com.example.mimimimetr.dto.CatGameDto;
import com.example.mimimimetr.service.game.CatGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final CatGameService catGameService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String playGame(Model model, Principal principal) {
        String email = principal.getName();
        List<CatGameDto> catPairs = catGameService.getCatListForGame(email);

        if (catPairs.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute("catPairs", catPairs);

        return "game";
    }


    @PostMapping("/vote")
    public String submitVote(Principal principal, @RequestParam("votedCatId") Long votedCatId) {
        String emailCurrentUser = principal.getName();
        catGameService.addLike(emailCurrentUser, votedCatId);

        return "redirect:/game";
    }
}
