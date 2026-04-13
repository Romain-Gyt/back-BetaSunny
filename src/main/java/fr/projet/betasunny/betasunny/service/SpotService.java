package fr.projet.betasunny.betasunny.service;

import fr.projet.betasunny.betasunny.dto.spot.SpotRequest;
import fr.projet.betasunny.betasunny.dto.spot.SpotResponse;

import java.util.List;


public interface SpotService {
    SpotResponse createSpot(SpotRequest request);
    SpotResponse updateSpot(Long id, SpotRequest request);
    SpotResponse getSpotById(Long id);
    List<SpotResponse> getAllSpots();
    void deleteSpot(Long id);
}
