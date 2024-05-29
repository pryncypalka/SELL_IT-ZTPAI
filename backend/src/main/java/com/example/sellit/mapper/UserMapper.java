package com.example.sellit.mapper;

import com.example.sellit.dto.UserDto;

import com.example.sellit.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .photoPath(user.getPhotoPath())
                .username(user.getRealUsername())
                .build();
    }

    public static User toEntity(UserDto usersDto) {
        return User.builder()
                .userId(usersDto.getUserId())
                .email(usersDto.getEmail())
                .photoPath(usersDto.getPhotoPath())
                .username(usersDto.getUsername())
                .build();
    }
}