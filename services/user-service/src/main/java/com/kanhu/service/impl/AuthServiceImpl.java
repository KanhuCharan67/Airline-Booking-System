package com.kanhu.service.impl;

import com.kanhu.config.JwtProvider;
import com.kanhu.config.SpringConfig;
import com.kanhu.enums.UserRole;
import com.kanhu.mapper.UserMapper;
import com.kanhu.model.User;
import com.kanhu.payload.dto.UserDTO;
import com.kanhu.payload.response.AuthResponse;
import com.kanhu.repository.UserRepository;
import com.kanhu.service.AuthService;
import com.kanhu.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final JwtProvider jwtProvider;
    private  final CustomUserDetailsService customUserDetailsService;
    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if(existingUser!=null){
            throw new Exception("Email already registered");
        }
        if(req.getRole() == UserRole.ROLE_SYSTEM_ADMIN){
            throw new Exception("you cant signup system admin");
        }
        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                newUser.getEmail(), newUser.getPassword()
        );
        String jwt= jwtProvider.generteToken(
                authentication, newUser.getId()
        );
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUserDto(UserMapper.toDto(newUser));
        authResponse.setTitle("Welcome "+newUser.getFullName());
        authResponse.setMessage("Registered SuccessFully");
        return authResponse;
    }
    @Override
    public AuthResponse login(String email, String password) throws Exception {
        Authentication authenticate = authenticate(email, password);
        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        String jwt=jwtProvider.generteToken(authenticate,user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUserDto(UserMapper.toDto(user));
        authResponse.setTitle("Welcome "+user.getFullName());
        authResponse.setMessage("Login SuccessFully");
        return authResponse;
    }
    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if(userDetails==null){
            throw new UsernameNotFoundException(email);
        }
        if(!passwordEncoder.matches(
                password, userDetails.getPassword()
        )){
            throw new Exception("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}
