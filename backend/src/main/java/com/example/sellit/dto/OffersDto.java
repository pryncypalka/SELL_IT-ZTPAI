package com.example.sellit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OffersDto {
    private Long offerId;
    private BigDecimal price;
    private Timestamp createdAt;
    private String title;
    private String description;
    private Long userId;

}