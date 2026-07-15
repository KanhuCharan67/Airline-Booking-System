package com.kanhu.mapper;

import com.kanhu.model.User;
import com.kanhu.payload.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toDto(User user){
        if(user==null)return null;
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .lastLogin(user.getLastLogin())
                .phone(user.getPhone())
                .build();
    }
    public static List<UserDTO> toDtoList(List<User> users){
        return users.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
