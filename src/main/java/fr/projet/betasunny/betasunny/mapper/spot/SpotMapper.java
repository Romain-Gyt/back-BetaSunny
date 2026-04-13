package fr.projet.betasunny.betasunny.mapper.spot;

import fr.projet.betasunny.betasunny.bo.Spot;
import fr.projet.betasunny.betasunny.dto.spot.SpotRequest;
import fr.projet.betasunny.betasunny.dto.spot.SpotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpotMapper {
    SpotResponse toSpotResponse(Spot spot);
    Spot toSpot(SpotRequest request);
    void updateSpot(SpotRequest request, @MappingTarget Spot spot);
}
