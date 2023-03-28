package com.example;

import com.example.repos.AccountRepo;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    // returns the SecurityUserPrincipal that was found for Spring Security to check the password
    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountRepo.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserPrincipal(account);
    }
}
