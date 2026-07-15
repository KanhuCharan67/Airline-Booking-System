package com.kanhu.payload.response;

import com.kanhu.payload.dto.UserDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String jwt;
    private String message;
    private String title;
    private UserDTO userDto;
}
