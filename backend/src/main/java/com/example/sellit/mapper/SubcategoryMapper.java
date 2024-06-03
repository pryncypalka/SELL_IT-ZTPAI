package com.example.sellit.mapper;

import com.example.sellit.dto.SubcategoryDto;
import com.example.sellit.model.Subcategory;
import org.springframework.stereotype.Component;

@Component
public class SubcategoryMapper {

    public static SubcategoryDto toDto(Subcategory subcategory) {
        return SubcategoryDto.builder()
                .subcategoryId(subcategory.getSubcategoryId())
                .categoryId(subcategory.getCategory().getCategoryId())
                .subcategoryName(subcategory.getSubcategoryName())
                .build();
    }
}