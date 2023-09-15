package com.example.mimimimetr.security.details;

import com.example.mimimimetr.exception.CatNotFoundException;
import com.example.mimimimetr.repository.CatRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountCatDetailsService implements UserDetailsService {
    private final CatRepositoryJpa catRepositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return new AccountCatDetails(catRepositoryJpa.findByName(name)
                .orElseThrow(() -> new CatNotFoundException("Cat not found")));
    }
}
