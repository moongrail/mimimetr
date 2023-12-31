package com.example.mimimimetr.controller;

import com.example.mimimimetr.service.CatRootService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
@Cacheable
public class RootController {
    private final CatRootService catRootService;


    @GetMapping
    public String getRootPage(Model model) {
        log.info("Starting 'get /'");
        model.addAttribute("topTenCatList", catRootService.getTopTenCatList());
        return "root";
    }
}
