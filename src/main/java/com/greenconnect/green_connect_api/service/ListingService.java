package com.greenconnect.green_connect_api.service;

import com.greenconnect.green_connect_api.entity.Listing;
import com.greenconnect.green_connect_api.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Listing getListingById(Long id) {
        return listingRepository.findById(id).orElse(null);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}
