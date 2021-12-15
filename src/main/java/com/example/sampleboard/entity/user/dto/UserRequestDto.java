package com.example.sampleboard.entity.user.dto;

import com.example.sampleboard.entity.user.User;
import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private String auth;

    public User toEntity() {
        return User.userRegistration(email, password, auth);
    }
}
