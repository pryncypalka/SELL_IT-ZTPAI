package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {
    private Long itemId;
    private String itemName;
    private Long subcategoryId;
    private String categoryName;
    private String subcategoryName;


}