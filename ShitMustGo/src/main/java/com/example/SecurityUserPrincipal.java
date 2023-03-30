package com.example;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecurityUserPrincipal implements UserDetails {

    // our user object is wrapped inside the SecurityUserPrincipal object
    private Account account;

    public SecurityUserPrincipal(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    // the getPassword method in SecurityUserPrincipal is using the password in our account object
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    // the getUsername method in SecurityUserPrincipal is using the username in our account object
    @Override
    public String getUsername() {
        return account.getUsername();
    }

    // we need to override these 4 methods since they exist in the interface, we could add columns for these values in the database and variables to delegate to in our admin object
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
