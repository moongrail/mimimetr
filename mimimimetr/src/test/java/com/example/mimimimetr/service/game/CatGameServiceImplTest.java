package com.example.mimimimetr.service.game;

import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.Like;
import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.repositories.LikesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatGameServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @Mock
    private LikesRepository likesRepository;

    @InjectMocks
    private CatGameServiceImpl catGameService;

    private Cat currentCat;
    private Cat votedCat;

    @BeforeEach
    public void setUp() {
        currentCat = Cat.builder()
                .rateCat(10L)
                .id(1L)
                .name("Current Cat")
                .email("test@test.com")
                .likes(new HashSet<>())
                .build();
        votedCat = Cat.builder()
                .rateCat(10L)
                .id(2L)
                .name("votedCat")
                .email("votedCat@test.com")
                .likes(new HashSet<>())
                .build();
    }

    @Test
    public void testAddLike_whenCatHasNotLikedVotedCatBefore_thenLikeAdded() {
        when(catRepository.findByEmail(anyString())).thenReturn(Optional.of(currentCat));
        when(catRepository.findById(anyLong())).thenReturn(Optional.of(votedCat));

        catGameService.addLike("test@test.com", 1L);

        verify(likesRepository, times(1)).save(any(Like.class));
        verify(catRepository, times(1)).save(any(Cat.class));
    }

    @Test
    public void testAddLike_whenCatHasLikedVotedCatBefore_thenLikeNotAdded() {
        currentCat.getLikes().add(Like.builder().votedCat(votedCat).build());
        when(catRepository.findByEmail(anyString())).thenReturn(Optional.of(currentCat));
        when(catRepository.findById(anyLong())).thenReturn(Optional.of(votedCat));

        catGameService.addLike("test@test.com", 1L);

        verify(likesRepository, times(0)).save(any(Like.class));
        verify(catRepository, times(0)).save(any(Cat.class));
    }

    @Test
    public void testAddLike_whenCatNotFound_thenEntityNotFoundExceptionThrown() {
        when(catRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
                () -> catGameService.addLike("test@test.com", 1L));

        assertEquals("Cat not found", entityNotFoundException.getMessage());
    }
}