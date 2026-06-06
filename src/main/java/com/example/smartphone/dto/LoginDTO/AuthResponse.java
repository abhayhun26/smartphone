package com.example.smartphone.dto.LoginDTO;

import com.example.smartphone.entity.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthResponse {
    private String message;
    private String token;
    private String email;
    private Role role;
}
