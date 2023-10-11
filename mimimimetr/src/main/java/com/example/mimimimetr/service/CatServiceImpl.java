package com.example.mimimimetr.service;

import com.example.mimimimetr.repository.CatRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {
    private final CatRepositoryJpa catRepositoryJpa;

}
