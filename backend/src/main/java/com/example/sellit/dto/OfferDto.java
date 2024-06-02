package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto {
    private Long offerId;
    private BigDecimal price;
    private Long itemId;
    private Timestamp createdAt;
    private String title;
    private String description;
    private Long userId;
    private String firstPhoto;

}