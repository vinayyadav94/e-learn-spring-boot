package com.elearn.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.config.security.JwtUtil;
import com.elearn.app.dtos.CustomUserDetail;
import com.elearn.app.dtos.JwtResponse;
import com.elearn.app.dtos.LoginRequest;
import com.elearn.app.dtos.UserDto;
import com.elearn.app.entities.User;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtil;

    private ModelMapper modelMapper;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
            JwtUtil jwtUtil, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody LoginRequest loginRequest) {

        try{

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authenticated = authenticationManager.authenticate(authenticationToken); //we have made AuthenticationManager bean in securityConfig

        }
        catch(AuthenticationException ex){

            throw new BadCredentialsException("Invalid user details!!");

        }

        //authenticated
        CustomUserDetail userDetails = (CustomUserDetail)userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        User user = userDetails.getUser();

        JwtResponse build = JwtResponse.builder()
                    .token(token)
                    .user(modelMapper.map(user, UserDto.class))
                    .build();

        return ResponseEntity.ok(build);

    }

}
