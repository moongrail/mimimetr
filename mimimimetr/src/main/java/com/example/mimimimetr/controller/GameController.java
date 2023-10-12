package com.example.mimimimetr.controller;

import com.example.mimimimetr.service.CatGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
@Slf4j
public class GameController {
    private final CatGameService catGameService;
}
