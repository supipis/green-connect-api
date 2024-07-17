package com.greenconnect.green_connect_api.service;

import com.greenconnect.green_connect_api.entity.Listing;
import com.greenconnect.green_connect_api.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    private static final String UPLOAD_DIR = "uploads/";

    public Listing saveListing(MultipartFile image, String name, String category, String location, int quantity) {
        // Save the file to the server
        String fileName = image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }

        // Create and save the listing
        Listing listing = new Listing();
        listing.setImage(filePath.toString()); // Save the file path or URL
        listing.setName(name);
        listing.setCategory(category);
        listing.setLocation(location);
        listing.setQuantity(quantity);
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