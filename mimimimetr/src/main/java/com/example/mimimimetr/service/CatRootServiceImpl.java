package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.CatDto;
import com.example.mimimimetr.repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mimimimetr.mapper.CatMapper.catDtoListFromCats;

@Service
@RequiredArgsConstructor
public class CatRootServiceImpl implements CatRootService {
    private final CatRepository catRepository;

    @Override
    public List<CatDto> getTopTenCatList() {
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.DESC, "rateCat");

        return  catDtoListFromCats(catRepository
                .findTop10(pageable)
                .getContent());
    }
}
