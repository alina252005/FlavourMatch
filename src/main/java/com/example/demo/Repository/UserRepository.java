package com.example.demo.Repository;

import com.example.demo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findUserEntitiesByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
