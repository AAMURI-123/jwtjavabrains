package com.umerscode.jwtjavabrains.Auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthRequest {

    private String email;
    private String password;
}
