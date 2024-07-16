package com.greenconnect.green_connect_api.service;

import com.greenconnect.green_connect_api.entity.Listing;
import com.greenconnect.green_connect_api.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public List<Listing> getAllListings() {
        return (List<Listing>) listingRepository.findAll();
    }

    public Listing getListingById(Long id) {
        Optional<Listing> listing = listingRepository.findById(id);
        return listing.orElse(null);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }

    public Listing updateListing(Long id, Listing updatedListing) {
        return listingRepository.findById(id)
                .map(existingListing -> {
                    existingListing.setImage(updatedListing.getImage());
                    existingListing.setName(updatedListing.getName());
                    existingListing.setCategory(updatedListing.getCategory());
                    existingListing.setLocation(updatedListing.getLocation());
                    existingListing.setQuantity(updatedListing.getQuantity());
                    return listingRepository.save(existingListing);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found with id " + id));
    }
}
