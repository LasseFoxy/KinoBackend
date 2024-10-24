package com.example.kinobackend.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // For testing, we hardcode a user with the username "testuser"
        if ("testuser".equals(username)) {
            return new User("testuser", "{noop}password", new ArrayList<>()); // The password here is "password"
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
