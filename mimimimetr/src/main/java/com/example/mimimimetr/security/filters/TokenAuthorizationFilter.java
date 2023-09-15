package com.example.mimimimetr.security.filters;

import com.example.mimimimetr.model.User;
import com.example.mimimimetr.repository.UserRepositoryJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    private UserRepositoryJpa userRepository;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(SecurityConfiguration.LOGIN_FILTER_PROCESSES_URL)) {
            filterChain.doFilter(request, response);
        } else {
                Optional<User> account = getAccountStateAndRole(token);

                if (account.isPresent()) {
                    AccountUserDetails userDetails = new AccountUserDetails(account.get());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(token, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                } else {
                    logger.warn("Wrong token");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "user not found with token"));
                }
            } else {
                logger.warn("Token is missing");
                filterChain.doFilter(request, response);
            }
        }

    }
}
