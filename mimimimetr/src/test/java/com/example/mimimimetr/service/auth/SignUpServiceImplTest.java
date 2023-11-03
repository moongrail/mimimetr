package com.example.mimimimetr.service.auth;

import com.example.mimimimetr.dto.SignUpForm;
import com.example.mimimimetr.exception.UniqueEmailCatException;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.model.CatImage;
import com.example.mimimimetr.repositories.CatImageRepository;
import com.example.mimimimetr.repositories.CatRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.mimimimetr.mapper.CatMapper.toCatSignUp;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @Mock
    private CatImageRepository catImageRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignUpServiceImpl signUpService;

    private SignUpForm signUpForm;

    @BeforeEach
    void setUp() {
        signUpForm = SignUpForm.builder()
                .email("test@test.com")
                .password("password")
                .name("test")
                .avatar(new MockMultipartFile("avatar", new byte[0]))
                .build();
    }

    @Test
    @DisplayName("Test signup method when the email is unique")
    @SneakyThrows
    void testSignUp_whenEmailIsUnique_thenSuccess() {
        when(catRepository.existsByEmail(signUpForm.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(signUpForm.getPassword())).thenReturn("encodedPassword");
        Cat cat = toCatSignUp(signUpForm);

        when(catRepository.save(any())).thenReturn(cat);

        cat.setPassword("encodedPassword");

        signUpService.signUp(signUpForm);

        verify(catRepository, times(1)).existsByEmail(signUpForm.getEmail());
        verify(catRepository, times(1)).save(cat);
        verify(catImageRepository, times(1)).save(any(CatImage.class));
    }

    @Test
    @DisplayName("Test signup method when the email already exists")
    void testSignUp_whenEmailExists_ThenThrowUniqueEmailCatException() {
        when(catRepository.existsByEmail(signUpForm.getEmail())).thenReturn(true);

        UniqueEmailCatException uniqueEmailCatException = assertThrows(UniqueEmailCatException.class,
                () -> signUpService.signUp(signUpForm));

        assertEquals("Email already exists", uniqueEmailCatException.getMessage());

        verify(catRepository, times(1)).existsByEmail(signUpForm.getEmail());
        verify(catRepository, times(0)).save(any(Cat.class));
        verify(catImageRepository, times(0)).save(any(CatImage.class));
    }
}