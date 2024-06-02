package com.example.sellit.service.offer;

import com.example.sellit.model.Item;
import com.example.sellit.model.Offer;
import com.example.sellit.model.Photo;
import com.example.sellit.model.User;
import com.example.sellit.repository.OfferRepository;
import com.example.sellit.repository.PhotoRepository;
import com.example.sellit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OfferServiceImp implements OfferService {

    private final OfferRepository offerRepository;
    private final UserService userService;
    private final PhotoRepository photoRepository;

    @Autowired
    public OfferServiceImp(OfferRepository offerRepository, UserService userService, PhotoRepository photoRepository) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.photoRepository = photoRepository;
    }

    @Override
    public List<Offer> getAllOffers(Long userId) {
        return offerRepository.findAllByUserId(userId);
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("offer with id " + id + " does not exist"));
    }

    @Override
    public Offer addOffer(Offer offer, Long userId, Item item) {
        User user = userService.getUser(userId);
        offer.setUser(user);
        offer.setItem(item);
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        Offer existingoffer = offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("offer with id " + id + " does not exist"));

        Optional.ofNullable(offer.getTitle()).ifPresent(existingoffer::setTitle);
        Optional.ofNullable(offer.getDescription()).ifPresent(existingoffer::setDescription);
        Optional.ofNullable(offer.getPrice()).ifPresent(existingoffer::setPrice);

        return offerRepository.save(existingoffer);
    }

    @Override
    @Transactional
    public void deleteOffer(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));

        for (Photo photo : offer.getPhotos()) {
            photoRepository.delete(photo);
        }
        offerRepository.delete(offer);
    }
    @Override
    public Offer updateOfferPhotos(Long id, List<String> photoPaths) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Offer with id " + id + " does not exist"));
        for (String photoPath : photoPaths) {
            Photo photo = new Photo();
            photo.setPhotoPath(photoPath);
            photo.setOffer(offer);
            photoRepository.save(photo);
        }
        return offerRepository.save(offer);
    }
}