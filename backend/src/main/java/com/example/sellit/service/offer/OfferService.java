package com.example.sellit.service.offer;

import com.example.sellit.model.Item;
import com.example.sellit.model.Offer;


import java.util.List;

public interface OfferService {
    List<Offer> getAllOffers(Long userId);

    Offer getOfferById(Long id);

    Offer addOffer(Offer offer, Long userId, Item item);

    Offer updateOffer(Long id, Offer offer);

    void deleteOffer(Long id);

    Offer updateOfferPhotos(Long id, List<String> photos);

    List<String> getPhotoPathsByOfferId(Long offerId);
}
