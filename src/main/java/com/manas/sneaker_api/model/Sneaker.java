package com.manas.sneaker_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sneakers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "page_url")
    private String pageUrl;

    private String gender;

    private boolean fresh;

    private String category;

    private String image;

    @OneToMany(
            mappedBy = "sneaker",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<SneakerSize> sizes = new ArrayList<>();
}
