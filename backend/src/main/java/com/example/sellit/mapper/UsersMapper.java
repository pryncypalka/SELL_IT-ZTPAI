package com.example.sellit.mapper;

import com.example.sellit.dto.UsersDto;
import com.example.sellit.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public static UsersDto toDto(Users user) {
        return UsersDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .photoPath(user.getPhotoPath())
                .username(user.getUsername())
                .build();
    }

    public static Users toEntity(UsersDto usersDto) {
        return Users.builder()
                .userId(usersDto.getUserId())
                .email(usersDto.getEmail())
                .photoPath(usersDto.getPhotoPath())
                .username(usersDto.getUsername())
                .build();
    }
}