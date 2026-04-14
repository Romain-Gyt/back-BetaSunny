package fr.projet.betasunny.betasunny.dto.spot;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SpotRequest(
        @NotBlank(message = " {spot.name.blank}")
        @Min(value = 2,message = "{spot.name.min}")
        @Max(value = 50, message = "{spot.name.max}")
        String name,
        @NotNull
        @Min(value = -90, message = "{spot.latitude.range}")
        @Max(value = 90, message = "{spot.latitude.range}")
        Double latitude,
        @NotNull
        @Min(value = -180, message = "{spot.longitude.range}")
        @Max(value = 180, message = "{spot.longitude.range}")
        Double longitude,
        @NotNull
        @Min(value = 0, message = "{spot.azimut.range}")
        @Max(value = 360, message = "{spot.azimut.range}")
        Integer azimut
) {
}
