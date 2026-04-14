package fr.projet.betasunny.betasunny.dto.spot;

import java.time.LocalDateTime;

public record SpotResponse(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Integer azimut,
        LocalDateTime createdAt
) {
}
