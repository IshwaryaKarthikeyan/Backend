package com.hexa.amazecare.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class GenUserPrincipal implements UserDetails {

    private GenUser user;  // The GenUser object representing the actual user
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor that initializes the user and assigns their role as authority
    public GenUserPrincipal(GenUser user) {
        this.user = user;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    // Another constructor for flexibility, allowing authorities to be passed in
    public GenUserPrincipal(GenUser user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    // Method to return the user's authorities (roles)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Method to return the user's password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Method to return the user's email (username in this case)
    @Override
    public String getUsername() {
        return user.getEmail();
    }
    // Custom method to return the full GenUser object
    public GenUser getUser() {
        return this.user;  // Return the actual GenUser object
    }
}
