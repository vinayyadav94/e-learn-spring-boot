package com.elearn.app.dtos;

import java.util.Collection;
//import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;

public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    // To be implemented when we will use roles/authorization
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //create and return user roles/authority
        //GrantedAuthority

        //Set<GrantedAuthority> authorities = new HashSet<>();

        Set<Role> roles = user.getRoles();

        Set<SimpleGrantedAuthority> authorities = roles
            .stream()
            .map( role -> new SimpleGrantedAuthority(role.getRoleName()))
            .collect(Collectors.toSet());
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

}
