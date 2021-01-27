package com.example.TestSecurity.repos;

import com.example.TestSecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author karpeykin
 * @Date 22.01.2021
 */
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
