package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllegroIntegrationDataDto {
    private Long idIntegration;
    private Long userId;
    private String accessToken;
    private String refreshToken;
    private LocalDateTime tokenExpiry;
    private String allegroDomain;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}