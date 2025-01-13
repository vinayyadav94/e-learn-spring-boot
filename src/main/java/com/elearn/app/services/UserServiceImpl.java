package com.elearn.app.services;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.UserDto;
import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.RoleRepo;
import com.elearn.app.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private ModelMapper modelMapper;

    private RoleRepo roleRepo;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, RoleRepo roleRepo,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserDto dto) {
        
        User user = modelMapper.map(dto, User.class);

        user.setUserId(UUID.randomUUID().toString());
        user.setCreateAt(new Date());
        user.setEmailVerified(false);
        user.setSmsVerified(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepo.findByRoleName(AppConstants.ROLE_GUEST).orElseThrow(() -> new ResourceNotFoundException("role not found! contact with Admin!"));
        user.assignRole(role);

        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getById(String userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        return modelMapper.map(user, UserDto.class);
    }

}
