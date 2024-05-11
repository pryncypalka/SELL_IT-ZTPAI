package com.example.sellit.mapper;

import com.example.sellit.dto.ItemDto;
import com.example.sellit.models.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item) {
        return ItemDto.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .build();
    }

    public Item toEntity(ItemDto itemDto) {

        return Item.builder()
                .itemId(itemDto.getItemId())
                .itemName(itemDto.getItemName())
                .build();
    }
}