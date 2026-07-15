package com.kanhu.controller;

import com.kanhu.model.User;
import com.kanhu.payload.dto.UserDTO;
import com.kanhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    @GetMapping("/profile")
     public ResponseEntity<UserDTO> getUserProfile(
             @RequestHeader("X-User-Email") String email
     ) throws Exception {
         UserDTO user = userService.getUserByEmail(email);
         return ResponseEntity.ok(user);
     }
     @GetMapping("/{userId}")
     public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) throws Exception {
         UserDTO userById = userService.getUserById(userId);
         return ResponseEntity.ok(userById);
     }
    @GetMapping()
     public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
     }

}
