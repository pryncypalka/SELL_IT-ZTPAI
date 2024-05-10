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
public class TemplatesDto {
    private Long templateId;
    private Long itemId;
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private Boolean isPublic;
}