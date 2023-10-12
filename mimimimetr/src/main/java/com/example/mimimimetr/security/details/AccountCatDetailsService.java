package com.example.mimimimetr.security.details;

import com.example.mimimimetr.exception.EntityNotFoundException;
import com.example.mimimimetr.repositories.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCatDetailsService implements UserDetailsService {
    private final CatRepository catRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AccountCatDetails(catRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Cat not found")));
    }
}
