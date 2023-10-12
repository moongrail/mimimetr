package com.example.mimimimetr.controller;

import com.example.mimimimetr.service.CatImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImagesController {
    private final CatImageService catImageService;

    @GetMapping("/{catId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long catId) {
        byte[] avatar = catImageService.getImage(catId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(avatar.length);

        return new ResponseEntity<>(avatar, headers, HttpStatus.OK);
    }
}
