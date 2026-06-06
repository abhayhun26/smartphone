package com.example.smartphone.service.impl;

import com.example.smartphone.dto.LoginDTO.AuthRequest;
import com.example.smartphone.dto.LoginDTO.AuthResponse;
import com.example.smartphone.dto.LoginDTO.RegisterRequest;
import com.example.smartphone.entity.Role;
import com.example.smartphone.entity.Users;
import com.example.smartphone.exceptions.UserException;
import com.example.smartphone.repository.UserRepository;
import com.example.smartphone.service.AuthService;
import com.example.smartphone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //REGISTER
    @Override
    public AuthResponse register(RegisterRequest register) {
        if(userRepository.existsByEmail(register.getEmail())){
            throw new UserException("User Already Exists!");
        }
        Users user = Users.builder()
                .email(register.getEmail())
                .name(register.getName())
                .password(passwordEncoder.encode(register.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse("User Registers!",token, user.getEmail(), user.getRole());
    }

    //LOGIN
    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Users user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UserException("User Doesn't Exist!"));
        String token = jwtUtil.generateToken(user.getEmail());
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new UserException("Invalid Email or Password!");
        }
        return new AuthResponse("Logged In Successfully!", token, user.getEmail(), user.getRole());
    }
}
