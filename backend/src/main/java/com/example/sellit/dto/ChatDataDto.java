package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatDataDto {
    private Long promptId;
    private Long userId;
    private Integer usedFreeTrials;
    private Integer tokenUsed;
}