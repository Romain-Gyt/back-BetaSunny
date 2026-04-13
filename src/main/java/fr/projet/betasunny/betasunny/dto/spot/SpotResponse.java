package fr.projet.betasunny.betasunny.dto.spot;

public record SpotResponse(
        Long id,
        String name,
        Double latitude,
        Double longitude,
        Integer Azimuth
) {
}
