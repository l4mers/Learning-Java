package com.users.users.controller.repository;

import com.users.users.model.UserData;
import com.users.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MySqlRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Users getUsersByEmail(String email);
    Users getUsersByUsername(String username);
}
