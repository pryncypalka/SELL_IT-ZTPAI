package com.example.sellit.mapper;

import com.example.sellit.dto.ItemDto;
import com.example.sellit.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .subcategoryId(item.getSubcategory().getSubcategoryId())
                .categoryName(item.getSubcategory().getCategory().getCategoryName())
                .subcategoryName(item.getSubcategory().getSubcategoryName())
                .build();
    }

    public static Item toEntity(ItemDto itemDto) {

        return Item.builder()
                .itemId(itemDto.getItemId())
                .itemName(itemDto.getItemName())
                .build();
    }
}