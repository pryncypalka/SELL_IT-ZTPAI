package com.example.sellit.mapper;

import com.example.sellit.dto.CategoriesDto;
import com.example.sellit.model.Categories;
import org.springframework.stereotype.Component;

@Component
public class CategoriesMapper {

    public CategoriesDto toDto(Categories categories) {
        return CategoriesDto.builder()
                .categoryId(categories.getCategoryId())
                .categoryName(categories.getCategoryName())
                .build();
    }

    public Categories toEntity(CategoriesDto categoriesDto) {

        return Categories.builder().
                categoryId(categoriesDto.getCategoryId()).
                categoryName(categoriesDto.getCategoryName()).
                build();
    }
}