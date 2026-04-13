package fr.projet.betasunny.betasunny.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp,
        List<String> errors
) {
}
