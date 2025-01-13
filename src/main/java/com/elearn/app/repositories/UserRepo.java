package com.elearn.app.repositories;

import com.elearn.app.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, String> {

    //load kar sake user ko with username
    Optional<User> findByEmail(String email);

}
