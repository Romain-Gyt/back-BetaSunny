package fr.projet.betasunny.betasunny.controller;

import fr.projet.betasunny.betasunny.bo.Spot;
import fr.projet.betasunny.betasunny.dto.spot.SpotRequest;
import fr.projet.betasunny.betasunny.dto.spot.SpotResponse;
import fr.projet.betasunny.betasunny.service.SpotService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor
@Validated
@RequestMapping("/spot")
public class SpotController {
    private final SpotService spotService;

    @GetMapping
    public ResponseEntity<List<SpotResponse>> getAllSpots() {
        return ResponseEntity.ok(spotService.getAllSpots());
    }

    @PostMapping
    public ResponseEntity<SpotResponse> createSpot(@Valid @RequestBody SpotRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spotService.createSpot(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpotResponse> updateSpot(
            @Valid @RequestBody SpotRequest request,
            @PathVariable
            @NotNull
            @Positive
            Long id
    ) {
        return ResponseEntity.ok(spotService.updateSpot(id,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpotResponse> getSpot(
            @PathVariable
            @NotNull
            @Positive
            Long id
    ){
        return ResponseEntity.ok(spotService.getSpotById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpot(
        @PathVariable
        @NotNull
        @Positive
        Long id
    ){
        spotService.deleteSpot(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
