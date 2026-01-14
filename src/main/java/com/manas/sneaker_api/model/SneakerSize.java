package com.manas.sneaker_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sneaker_sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SneakerSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int size;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sneaker_id")
    @JsonBackReference
    private Sneaker sneaker;
}
