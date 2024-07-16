package com.greenconnect.green_connect_api.repository;


import com.greenconnect.green_connect_api.entity.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Long> {
    }
