package com.example.sellit.mapper;

import com.example.sellit.dto.OfferDto;

import com.example.sellit.model.Offer;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    public static OfferDto toDto(Offer offer) {
        return OfferDto.builder()
                .offerId(offer.getOfferId())
                .price(offer.getPrice())
                .createdAt(offer.getCreatedAt())
                .itemId(offer.getItem().getItemId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .userId(offer.getUser().getUserId())
                .build();
    }
}