package com.manas.sneaker_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manas.sneaker_api.model.Sneaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${GEMINI_API_KEY}")
    private String apiKey;

    private final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent";

    public Map<String, Object> getSuggestion(String query, List<Sneaker> sneakers) {

        // Step 1: Filter sneakers
        List<Sneaker> filtered = sneakers.stream()
                .filter(s -> query.toLowerCase().contains("men") ?
                        s.getGender().equalsIgnoreCase("men") : true)
                .filter(s -> query.toLowerCase().contains("running") ?
                        s.getCategory().toLowerCase().contains("running") : true)
                .toList();

        // fallback
        if (filtered.isEmpty()) {
            filtered = sneakers;
        }

        // 🔥 Step 2: Build context
        StringBuilder context = new StringBuilder("Sneaker Database:\n");

        for (Sneaker s : filtered) {
            context.append("- ")
                    .append(s.getName())
                    .append(" | Category: ").append(s.getCategory())
                    .append(" | Gender: ").append(s.getGender())
                    .append(" | Fresh: ").append(s.isFresh())
                    .append("\n");
        }

        // Step 3: Prompt
        String prompt = "You are a sneaker recommendation expert.\n\n"
                + "User Query: " + query + "\n\n"
                + context
                + "\nRULES:\n"
                + "1. ONLY use sneakers from the database above.\n"
                + "2. Always return EXACTLY 3 results.\n"
                + "3. Return response in JSON format ONLY.\n\n"
                + "FORMAT:\n"
                + "{\n"
                + "  \"results\": [\n"
                + "    {\"name\": \"Sneaker Name\", \"reason\": \"Reason\"},\n"
                + "    {\"name\": \"Sneaker Name\", \"reason\": \"Reason\"},\n"
                + "    {\"name\": \"Sneaker Name\", \"reason\": \"Reason\"}\n"
                + "  ]\n"
                + "}";

        // 🔹 Request body
        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> part = new HashMap<>();
        part.put("parts", List.of(textPart));

        Map<String, Object> request = new HashMap<>();
        request.put("contents", List.of(part));

        // 🔹 Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-goog-api-key", apiKey);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        //  Step 4: Retry logic
        ResponseEntity<Map> response = null;
        int attempts = 0;

        while (attempts < 3) {
            try {
                response = restTemplate.postForEntity(API_URL, entity, Map.class);
                break;
            } catch (Exception e) {
                attempts++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }

        if (response == null) {
            return Map.of("error", "AI service is currently busy. Try again.");
        }

        //  Step 5: Extract + Convert JSON string → Map
        try {
            List<Map<String, Object>> candidates =
                    (List<Map<String, Object>>) response.getBody().get("candidates");

            Map<String, Object> content =
                    (Map<String, Object>) candidates.get(0).get("content");

            List<Map<String, Object>> parts =
                    (List<Map<String, Object>>) content.get("parts");

            String jsonText = parts.get(0).get("text").toString();

            // ✅ Convert string → JSON object
            return mapper.readValue(jsonText, Map.class);

        } catch (Exception e) {
            return Map.of("error", "Parsing failed", "raw", response.getBody());
        }
    }
}