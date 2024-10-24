package com.example.kinobackend.repository;

import com.example.kinobackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String name);
    //List<User> findUserByPasswordContains(String passwordPart);
}
