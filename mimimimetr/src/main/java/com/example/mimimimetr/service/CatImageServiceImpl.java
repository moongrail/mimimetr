package com.example.mimimimetr.service;

import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.model.CatImage;
import com.example.mimimimetr.repositories.CatImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatImageServiceImpl implements CatImageService {
    private final CatImageRepository catImageRepository;

    @Override
    public byte[] getImage(Long catId) {
        CatImage catImage = catImageRepository
                .findImage(catId).orElseThrow(() -> new EntityNotFoundException("Image not found"));

        return catImage.getContent();
    }
}
