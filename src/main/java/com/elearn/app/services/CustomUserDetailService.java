package com.elearn.app.services;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearn.app.dtos.CustomUserDetail;
import com.elearn.app.entities.User;
import com.elearn.app.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(() -> new BadCredentialsException("user not found in database"));
        
        return new CustomUserDetail(user);
    }

}
