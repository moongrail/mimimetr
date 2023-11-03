package com.example.mimimimetr.service.image;

import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.CatImage;
import com.example.mimimimetr.repositories.CatImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatImageServiceImplTest {

    @Mock
    private CatImageRepository catImageRepository;

    @InjectMocks
    private CatImageServiceImpl catImageService;

    private CatImage catImage;

    @BeforeEach
    void setUp() {
        Cat cat = new Cat();
        cat.setId(1L);

        catImage = new CatImage();
        catImage.setId(1L);
        catImage.setContent(new byte[0]);
        catImage.setCat(cat);
    }

    @Test
    @DisplayName("Test getImage method when the image exists")
    void testGetImage_whenImageExists_thenReturnContent() {
        when(catImageRepository.findImage(1L)).thenReturn(Optional.of(catImage));

        byte[] result = catImageService.getImage(1L);

        assertArrayEquals(new byte[0], result);
    }

    @Test
    @DisplayName("Test getImage method when the image does not exist")
    void testGetImage_whenImageDoesNotExist_thenThrowException() {
        when(catImageRepository.findImage(1L)).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
                () -> catImageService.getImage(1L));

        assertEquals("Image not found", entityNotFoundException.getMessage());
    }
}