package com.elearn.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.Role;


public interface RoleRepo extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(String roleName);

}
