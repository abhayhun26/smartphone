package com.example.smartphone.service;

import com.example.smartphone.dto.LoginDTO.AuthRequest;
import com.example.smartphone.dto.LoginDTO.AuthResponse;
import com.example.smartphone.dto.LoginDTO.RegisterRequest;
import org.springframework.stereotype.Component;


public interface AuthService {
    AuthResponse register(RegisterRequest register);
    AuthResponse login(AuthRequest request);
}
