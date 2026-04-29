package com.manas.sneaker_api.controller;

import com.manas.sneaker_api.model.Sneaker;
import com.manas.sneaker_api.service.SneakerService;
import com.manas.sneaker_api.service.AIService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/sneakers")
@CrossOrigin(origins = "*")
public class SneakerController {

    private final SneakerService sneakerService;
    private final AIService aiService;

    public SneakerController(SneakerService sneakerService, AIService aiService) {
        this.sneakerService = sneakerService;
        this.aiService = aiService;
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

    // ===================== AI SUGGESTION (NEW) =====================
 
@PostMapping("/ai/suggest")
public Map<String, Object> getSuggestion(@RequestBody String query) {

    Pageable pageable = PageRequest.of(0, 50); // get first 50 sneakers

    List<Sneaker> sneakers =
            sneakerService.getAllSneakers(pageable).getContent();

    return aiService.getSuggestion(query, sneakers);
}
}