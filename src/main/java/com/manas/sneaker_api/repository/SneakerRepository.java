package com.manas.sneaker_api.repository;

import com.manas.sneaker_api.model.Sneaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

    // Filtering + Pagination
    Page<Sneaker> findByGenderAndCategoryAndFresh(
            String gender,
            String category,
            Boolean fresh,
            Pageable pageable
    );
}
