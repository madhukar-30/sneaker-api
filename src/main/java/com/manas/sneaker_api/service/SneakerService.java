package com.manas.sneaker_api.service;

import com.manas.sneaker_api.model.Sneaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SneakerService {

    // CREATE
    Sneaker createSneaker(Sneaker sneaker);

    // READ
    Sneaker getSneakerById(Long id);
    Page<Sneaker> getAllSneakers(Pageable pageable);

    // UPDATE
    Sneaker updateSneaker(Long id, Sneaker sneaker);

    // DELETE
    void deleteSneaker(Long id);

    // FILTER + PAGINATION
    Page<Sneaker> filterSneakers(
            String gender,
            String category,
            Boolean fresh,
            Pageable pageable
    );
}
