package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryDto {
    private Long subcategoryId;
    private Long categoryId;
    private String subcategoryName;

}