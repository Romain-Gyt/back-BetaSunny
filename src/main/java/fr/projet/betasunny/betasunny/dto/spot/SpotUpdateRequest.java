package fr.projet.betasunny.betasunny.dto.spot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpotUpdateRequest(

        String name,
        @Min(-90) @Max(90)
        Double latitude,
        @Min(-180) @Max(180)
        Double longitude,
        @Min(0) @Max(360)
        Integer azimut
) {
}
