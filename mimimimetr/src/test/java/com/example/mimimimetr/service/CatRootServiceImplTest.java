package com.example.mimimimetr.service;

import com.example.mimimimetr.dto.CatDto;
import com.example.mimimimetr.mapper.CatMapper;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatRootServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatRootServiceImpl catRootService;

    private Cat cat;
    private CatDto catDto;
    private Pageable pageable;

    @BeforeEach
    public void setUp() {
        cat = Cat.builder()
                .id(1L)
                .name("Test Cat")
                .rateCat(10L)
                .build();

        catDto = CatMapper.catDtoFromCat(cat);

        pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "rateCat");
    }

    @Test
    public void testGetTopTenCatList_whenCatsExist_thenReturnCatDtoList() {
        Page<Cat> catPage = new PageImpl<>(Collections.singletonList(cat));

        when(catRepository.findTop10(pageable)).thenReturn(catPage);

        List<CatDto> result = catRootService.getTopTenCatList();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0)).isEqualTo(catDto);
    }

    @Test
    public void testGetTopTenCatList_whenNoCat_thenReturnEmptyList() {
        Page<Cat> catPage = Page.empty();

        when(catRepository.findTop10(pageable)).thenReturn(catPage);

        List<CatDto> result = catRootService.getTopTenCatList();

        assertThat(result).isEmpty();
    }
}