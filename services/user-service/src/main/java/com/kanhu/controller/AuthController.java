package com.kanhu.controller;

import com.kanhu.payload.dto.UserDTO;
import com.kanhu.payload.request.LoginRequest;
import com.kanhu.payload.response.AuthResponse;
import com.kanhu.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody @Valid UserDTO userDTO) throws Exception {
        AuthResponse authResponse = authService.signup(userDTO);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {
        AuthResponse authResponse = authService.login(loginRequest.getEmail(),loginRequest.getPassword());
        return ResponseEntity.ok(authResponse);
    }
}
