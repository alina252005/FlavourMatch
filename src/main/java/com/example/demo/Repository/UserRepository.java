package com.example.demo.Repository;

import com.example.demo.Entity.Role;
import com.example.demo.Entity.UserEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserEntitiesByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    UserEntity getByRole(Role role);

    Optional<UserEntity> findUserByUsername(String username);
}
