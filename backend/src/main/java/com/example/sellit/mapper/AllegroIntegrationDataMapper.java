package com.example.sellit.mapper;

import com.example.sellit.dto.AllegroIntegrationDataDto;
import com.example.sellit.models.AllegroIntegrationData;
import com.example.sellit.models.Users;
import org.springframework.stereotype.Component;

@Component
public class AllegroIntegrationDataMapper {

    public AllegroIntegrationDataDto toDto(AllegroIntegrationData allegroIntegrationData) {
        return AllegroIntegrationDataDto.builder()
                .idIntegration(allegroIntegrationData.getIdIntegration())
                .userId(allegroIntegrationData.getUsers().getUserId())
                .accessToken(allegroIntegrationData.getAccessToken())
                .refreshToken(allegroIntegrationData.getRefreshToken())
                .tokenExpiry(allegroIntegrationData.getTokenExpiry())
                .allegroDomain(allegroIntegrationData.getAllegroDomain())
                .build();
    }

    public AllegroIntegrationData toEntity(AllegroIntegrationDataDto allegroIntegrationDataDto) {

        return AllegroIntegrationData.builder()
                .idIntegration(allegroIntegrationDataDto.getIdIntegration())
                .users(Users.builder().userId(allegroIntegrationDataDto.getUserId()).build())
                .accessToken(allegroIntegrationDataDto.getAccessToken())
                .refreshToken(allegroIntegrationDataDto.getRefreshToken())
                .tokenExpiry(allegroIntegrationDataDto.getTokenExpiry())
                .allegroDomain(allegroIntegrationDataDto.getAllegroDomain())
                .build();
    }
}
