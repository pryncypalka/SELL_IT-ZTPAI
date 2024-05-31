package com.example.sellit.mapper;

import com.example.sellit.dto.CategoryDto;
import com.example.sellit.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build();
    }

    public static Category toEntity(CategoryDto categoriesDto) {

        return Category.builder().
                categoryId(categoriesDto.getCategoryId()).
                categoryName(categoriesDto.getCategoryName()).
                build();
    }
}