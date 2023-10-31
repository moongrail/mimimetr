package com.example.mimimimetr.security.filter;

import com.example.mimimimetr.dto.SignInForm;
import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.security.details.AccountCatDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Slf4j
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String TOKEN = "token";
    private final ObjectMapper mapper;
    private final CatRepository catRepository;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager,
                                     ObjectMapper mapper, CatRepository catRepository) {
        super(authenticationManager);
        this.mapper = mapper;
        this.catRepository = catRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            if (isJsonRequest(request)) {
                SignInForm signInForm = mapper.readValue(request.getReader(), SignInForm.class);
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(signInForm.getEmail(), signInForm.getPassword());
                return super.getAuthenticationManager().authenticate(token);
            } else {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                UsernamePasswordAuthenticationToken token =
                        new UsernamePasswordAuthenticationToken(email, password);
                return super.getAuthenticationManager().authenticate(token);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AccountCatDetails catDetails = (AccountCatDetails) authResult.getPrincipal();
        Cat cat = catDetails.getAccount();

        String token = UUID.randomUUID().toString();
        cat.setToken(token);
        catRepository.save(cat);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        mapper.writeValue(response.getWriter(), Collections.singletonMap(TOKEN, token));
        response.sendRedirect("/");
    }


    private boolean isJsonRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE);
    }
}
