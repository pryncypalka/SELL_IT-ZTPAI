package com.example.sellit.controller;
import com.example.sellit.dto.OfferDto;
import com.example.sellit.mapper.OfferMapper;
import com.example.sellit.model.Item;
import com.example.sellit.model.Offer;
import com.example.sellit.service.item.ItemService;
import com.example.sellit.service.offer.OfferService;
import com.example.sellit.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private final OfferService offerService;
    private final UserService userService;
    private final ItemService itemService;

    public OfferController(OfferService offerService, UserService userService, ItemService itemService) {
        this.offerService = offerService;
        this.userService = userService;
        this.itemService = itemService;

    }

    @GetMapping("/get-all")
    public ResponseEntity<List<OfferDto>> getOffers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        List<Offer> offers = offerService.getAllOffers(userId);
        List<OfferDto> offersDto = offers.stream()
                .map(OfferMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(offersDto, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable Long id) {
        Offer offer = offerService.getOfferById(id);
        return new ResponseEntity<>(OfferMapper.toDto(offer), HttpStatus.OK);
    }

    @PostMapping("/add/{itemId}")
    public ResponseEntity<OfferDto> addOffer(@RequestBody Offer Offer, @PathVariable Long itemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        Item item = itemService.getItemById(itemId);
        Offer newOffer = offerService.addOffer(Offer, userId, item);
        return new ResponseEntity<>(OfferMapper.toDto(newOffer), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable Long id, @RequestBody Offer Offer) {
        Offer updatedOffer = offerService.updateOffer(id, Offer);
        return new ResponseEntity<>(OfferMapper.toDto(updatedOffer), HttpStatus.OK);
    }

    @PostMapping(path = "/update-offer-images/{offerId}")
    public ResponseEntity<OfferDto> updateOfferImages(@PathVariable Long offerId, @RequestParam("files") MultipartFile[] files) {
        if (files.length > 10) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = userService.getUserIdByEmail(auth.getName());
        Offer offer = offerService.getOfferById(offerId);
        if (offer == null || !offer.getUser().getUserId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String fileName = auth.getName() + "_" + timestamp + "_" + file.getOriginalFilename();
            Path path = Paths.get("src/main/java/com/example/sellit/images/photos/" + fileName);
            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            fileNames.add(path.toString());
        }
        Offer updatedOffer = offerService.updateOfferPhotos(offerId, fileNames);
        return new ResponseEntity<>(OfferMapper.toDto(updatedOffer),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
