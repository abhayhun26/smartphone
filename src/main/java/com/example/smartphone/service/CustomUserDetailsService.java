package com.example.smartphone.service;

import com.example.smartphone.entity.Users;
import com.example.smartphone.exceptions.UserException;
import com.example.smartphone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserException {
        Users user = userRepository.findByEmail(username).orElseThrow(()-> new UserException("User doesn't exist!"));
        return new User(user.getEmail(),user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+ user.getRole().name())));
    }
}
