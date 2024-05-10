package com.example.sellit.dto;

import com.example.sellit.models.Subcategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDto {
    private Long categoryId;
    private String categoryName;

}