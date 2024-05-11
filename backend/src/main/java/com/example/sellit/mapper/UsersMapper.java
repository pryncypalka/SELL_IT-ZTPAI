package com.example.sellit.mapper;

import com.example.sellit.dto.UsersDto;
import com.example.sellit.models.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public UsersDto toDto(Users users) {
        return UsersDto.builder()
                .userId(users.getUserId())
                .email(users.getEmail())
                .photoPath(users.getPhotoPath())
                .username(users.getUsername())
                .build();
    }

    public Users toEntity(UsersDto usersDto) {
        return Users.builder()
                .userId(usersDto.getUserId())
                .email(usersDto.getEmail())
                .photoPath(usersDto.getPhotoPath())
                .username(usersDto.getUsername())
                .build();
    }
}