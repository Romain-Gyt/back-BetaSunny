package fr.projet.betasunny.betasunny.mapper.spot;

import fr.projet.betasunny.betasunny.bo.Spot;
import fr.projet.betasunny.betasunny.dto.spot.SpotRequest;
import fr.projet.betasunny.betasunny.dto.spot.SpotResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-13T14:48:39+0200",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.1.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class SpotMapperImpl implements SpotMapper {

    @Override
    public SpotResponse toSpotResponse(Spot spot) {
        if ( spot == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Double latitude = null;
        Double longitude = null;
        Integer azimut = null;

        id = spot.getId();
        name = spot.getName();
        latitude = spot.getLatitude();
        longitude = spot.getLongitude();
        azimut = spot.getAzimut();

        SpotResponse spotResponse = new SpotResponse( id, name, latitude, longitude, azimut );

        return spotResponse;
    }

    @Override
    public Spot toSpot(SpotRequest request) {
        if ( request == null ) {
            return null;
        }

        Spot.SpotBuilder spot = Spot.builder();

        spot.name( request.name() );
        spot.latitude( request.latitude() );
        spot.longitude( request.longitude() );
        spot.azimut( request.azimut() );

        return spot.build();
    }

    @Override
    public void updateSpot(SpotRequest request, Spot spot) {
        if ( request == null ) {
            return;
        }

        spot.setName( request.name() );
        spot.setLatitude( request.latitude() );
        spot.setLongitude( request.longitude() );
        spot.setAzimut( request.azimut() );
    }
}
