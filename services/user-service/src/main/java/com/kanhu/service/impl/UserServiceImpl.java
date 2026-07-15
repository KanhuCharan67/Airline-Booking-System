package com.kanhu.service.impl;

import com.kanhu.mapper.UserMapper;
import com.kanhu.model.User;
import com.kanhu.payload.dto.UserDTO;
import com.kanhu.repository.UserRepository;
import com.kanhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw  new Exception("User not Found");
        }
        return UserMapper.toDto(user);
    }

    @Override
    public UserDTO getUserById(Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found with this id "+ id ));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDtoList(users);
    }
}
