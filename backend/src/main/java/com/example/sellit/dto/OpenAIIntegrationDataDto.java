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
public class OpenAIIntegrationDataDto {
    private Long promptId;
    private Long userId;
    private String promptResult;
    private String promptText;
    private Integer usedFreeTrials;
    private Integer tokenUsed;
    private LocalDateTime promptCreatedAt;
}