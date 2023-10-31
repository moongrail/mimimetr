package com.example.mimimimetr.security.filter;

import com.example.mimimimetr.model.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import com.example.mimimimetr.security.config.WebSecurityConfig;
import com.example.mimimimetr.security.details.AccountCatDetails;
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
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {
    private final CatRepository catRepository;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().equals(WebSecurityConfig.LOGIN_FILTER_PROCESSES_URL)){
            filterChain.doFilter(request, response);
        }else {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring(7);
                Optional<Cat> byToken = catRepository.findByToken(token);

                if (byToken.isPresent()) {
                    AccountCatDetails catDetails = new AccountCatDetails(byToken.get());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(token,
                                    null, catDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);
                }else {
                    logger.warn("Token not found");
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    mapper.writeValue(response.getWriter(), "Доступ запрещен");
                }
            }else {
                logger.warn("Token not found");
                filterChain.doFilter(request, response);
            }
        }
    }
}
