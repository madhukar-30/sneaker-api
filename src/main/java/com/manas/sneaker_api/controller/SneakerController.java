package com.manas.sneaker_api.controller;

import com.manas.sneaker_api.model.Sneaker;
import com.manas.sneaker_api.service.SneakerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin(origins = "*")
public class SneakerController {

    private final SneakerService sneakerService;

    public SneakerController(SneakerService sneakerService) {
        this.sneakerService = sneakerService;
    }

    // ===================== READ (ALL + PAGINATION + FILTERING) =====================
    @GetMapping
    public Page<Sneaker> getSneakers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean fresh
    ) {
        Pageable pageable = PageRequest.of(page, size);

        if (gender != null && category != null && fresh != null) {
            return sneakerService.filterSneakers(gender, category, fresh, pageable);
        }

        return sneakerService.getAllSneakers(pageable);
    }

    // ===================== READ (BY ID) =====================
    @GetMapping("/{id}")
    public Sneaker getSneakerById(@PathVariable Long id) {
        return sneakerService.getSneakerById(id);
    }

    // ===================== CREATE =====================
    @PostMapping
    public Sneaker createSneaker(@RequestBody Sneaker sneaker) {
        return sneakerService.createSneaker(sneaker);
    }

    // ===================== UPDATE =====================
    @PutMapping("/{id}")
    public Sneaker updateSneaker(
            @PathVariable Long id,
            @RequestBody Sneaker sneaker
    ) {
        return sneakerService.updateSneaker(id, sneaker);
    }

    // ===================== DELETE =====================
    @DeleteMapping("/{id}")
    public void deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
    }
}
