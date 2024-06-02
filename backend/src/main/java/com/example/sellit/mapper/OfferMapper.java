package com.example.sellit.mapper;

import com.example.sellit.dto.OfferDto;

import com.example.sellit.model.Offer;
import com.example.sellit.model.Photo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferMapper {


    public static OfferDto toDto(Offer offer) {
        List<Photo> photos = offer.getPhotos();
        Photo firstPhoto = (photos != null && !photos.isEmpty()) ? photos.get(0) : null;
        String firstPhotoPath = firstPhoto == null ? null : firstPhoto.getPhotoPath();
        return OfferDto.builder()
                .offerId(offer.getOfferId())
                .price(offer.getPrice())
                .createdAt(offer.getCreatedAt())
                .itemId(offer.getItem().getItemId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .userId(offer.getUser().getUserId())
                .firstPhoto(firstPhotoPath)
                .build();
    }

}