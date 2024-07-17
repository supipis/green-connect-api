package com.greenconnect.green_connect_api.controller;


import com.greenconnect.green_connect_api.entity.Listing;
import com.greenconnect.green_connect_api.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/listings")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @PostMapping
    public Listing addListing(@RequestParam("image") MultipartFile image,
                              @RequestParam("name") String name,
                              @RequestParam("category") String category,
                              @RequestParam("location") String location,
                              @RequestParam("quantity") int quantity) {
        // Call service method to handle file upload and create listing
        return listingService.saveListing(image, name, category, location, quantity);
    }

    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public Listing getListingById(@PathVariable Long id) {
        return listingService.getListingById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
    }

    @PutMapping("/{id}")
    public Listing updateListing(@PathVariable Long id, @RequestBody Listing listing) {
        return listingService.updateListing(id, listing);
    }


    }
