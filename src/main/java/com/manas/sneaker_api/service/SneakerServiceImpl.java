package com.manas.sneaker_api.service;

import com.manas.sneaker_api.exception.ResourceNotFoundException;
import com.manas.sneaker_api.model.Sneaker;
import com.manas.sneaker_api.repository.SneakerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SneakerServiceImpl implements SneakerService {

    private final SneakerRepository sneakerRepository;

    public SneakerServiceImpl(SneakerRepository sneakerRepository) {
        this.sneakerRepository = sneakerRepository;
    }

    // CREATE
    @Override
    public Sneaker createSneaker(Sneaker sneaker) {
        sneaker.getSizes()
                .forEach(size -> size.setSneaker(sneaker));

        return sneakerRepository.save(sneaker);
    }

    // READ (by id)
    @Override
    public Sneaker getSneakerById(Long id) {
        return sneakerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sneaker not found with id: " + id)
                );
    }

    // READ (all with pagination)
    @Override
    public Page<Sneaker> getAllSneakers(Pageable pageable) {
        return sneakerRepository.findAll(pageable);
    }

    // UPDATE
    @Override
    public Sneaker updateSneaker(Long id, Sneaker updatedSneaker) {

        Sneaker existing = sneakerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sneaker not found with id: " + id)
                );

        existing.setName(updatedSneaker.getName());
        existing.setPageUrl(updatedSneaker.getPageUrl());
        existing.setGender(updatedSneaker.getGender());
        existing.setFresh(updatedSneaker.isFresh());
        existing.setCategory(updatedSneaker.getCategory());
        existing.setImage(updatedSneaker.getImage());

        // replace sizes safely
        existing.getSizes().clear();
        updatedSneaker.getSizes().forEach(size -> {
            size.setSneaker(existing);
            existing.getSizes().add(size);
        });

        return sneakerRepository.save(existing);
    }

    // DELETE
    @Override
    public void deleteSneaker(Long id) {
        Sneaker sneaker = sneakerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sneaker not found with id: " + id)
                );

        sneakerRepository.delete(sneaker);
    }

    // FILTER + PAGINATION
    @Override
    public Page<Sneaker> filterSneakers(
            String gender,
            String category,
            Boolean fresh,
            Pageable pageable
    ) {
        return sneakerRepository
                .findByGenderAndCategoryAndFresh(gender, category, fresh, pageable);
    }
}
