package com.kanhu.service;

import com.kanhu.payload.dto.UserDTO;
import com.kanhu.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signup(UserDTO req) throws Exception;
}
