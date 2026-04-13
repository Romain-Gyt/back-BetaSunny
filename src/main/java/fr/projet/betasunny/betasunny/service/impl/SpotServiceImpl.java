package fr.projet.betasunny.betasunny.service.impl;

import fr.projet.betasunny.betasunny.bo.Spot;
import fr.projet.betasunny.betasunny.dto.spot.SpotRequest;
import fr.projet.betasunny.betasunny.dto.spot.SpotResponse;
import fr.projet.betasunny.betasunny.mapper.spot.SpotMapper;
import fr.projet.betasunny.betasunny.repository.SpotRepository;
import fr.projet.betasunny.betasunny.service.SpotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SpotServiceImpl implements SpotService {
    private final SpotRepository spotRepository;
    private final SpotMapper spotMapper;

    @Override
    @Transactional
    public SpotResponse createSpot(SpotRequest request) {
        Spot spotToSave = spotMapper.toSpot(request);
        Spot savedSpot = spotRepository.save(spotToSave);
        return spotMapper.toSpotResponse(savedSpot);
    }

    @Override
    @Transactional
    public SpotResponse updateSpot(Long id, SpotRequest request) {
        Spot existing =spotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Spot not found"));
        spotMapper.updateSpot(request,existing);
        Spot updatedSpot = spotRepository.save(existing);
        return spotMapper.toSpotResponse(updatedSpot);
    }

    @Override
    public SpotResponse getSpotById(Long id) {
       Spot spot =  spotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Spot not found"));
       return spotMapper.toSpotResponse(spot);
    }

    @Override
    public List<SpotResponse> getAllSpots() {
       List<Spot> spots = spotRepository.findAll();
       return spots.stream()
               .map(spotMapper::toSpotResponse)
               .toList();
    }

    @Override
    @Transactional
    public void deleteSpot(Long id) {
        if (!spotRepository.existsById(id)) {
            throw new EntityNotFoundException("Spot not found");
        }
        spotRepository.deleteById(id);
    }
}
