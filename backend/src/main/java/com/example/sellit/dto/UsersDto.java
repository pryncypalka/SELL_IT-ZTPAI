package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    private Long userId;
    private String passwordHashed;
    private String email;
    private String photoPath;
    private String username;
    private LocalDateTime createdAt;
    private Set<Long> roleIds;
    private Set<Long> offerIds;
    private Set<Long> templateIds;
    private Long allegroIntegrationDataId;
    private Long openAIIntegrationDataId;
}