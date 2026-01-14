package com.manas.sneaker_api.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manas.sneaker_api.model.Sneaker;
import com.manas.sneaker_api.repository.SneakerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Profile("local") // only runs in local profile
public class SneakerDataLoader implements CommandLineRunner {

    private final SneakerRepository sneakerRepository;
    private final ObjectMapper objectMapper;

    public SneakerDataLoader(SneakerRepository sneakerRepository,
                             ObjectMapper objectMapper) {
        this.sneakerRepository = sneakerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        // prevent re-seeding every restart
        if (sneakerRepository.count() > 0) {
            return;
        }

        InputStream inputStream =
                getClass().getResourceAsStream("/sneakerData.json");

        List<Sneaker> sneakers =
                objectMapper.readValue(inputStream, new TypeReference<>() {});

        sneakers.forEach(s ->
                s.getSizes().forEach(size -> size.setSneaker(s))
        );

        sneakerRepository.saveAll(sneakers);

        System.out.println("✅ Sneaker data seeded successfully");
    }
}
