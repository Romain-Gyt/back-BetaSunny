package fr.projet.betasunny.betasunny.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // --- 1. ERREURS DE VALIDATION (@Valid) ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        List<String> validationKeys = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "validation." + error.getField() + "." + error.getCode().toLowerCase())
                .toList();

        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                SunnyErrorCode.VALIDATION_ERROR.getCode(),
                SunnyErrorCode.VALIDATION_ERROR.getMessage(),
                LocalDateTime.now(),
                validationKeys
        ));
    }

    // --- 2. RESSOURCE NON TROUVÉE (404) ---
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(
                SunnyErrorCode.RESOURCE_NOT_FOUND.getCode(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        ));
    }

    // --- 3. ERREURS SQL / BASE DE DONNÉES ---
    // On attrape DataAccessException qui est la racine des erreurs Spring Data / Hibernate
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseError(DataAccessException ex) {
        // LOG IMPORTANT : On log l'erreur réelle pour le développeur dans la console
        // Mais on ne l'envoie JAMAIS au client pour ne pas exposer la structure SQL
        System.err.println("Database Error: " + ex.getRootCause());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ApiErrorResponse(
                SunnyErrorCode.DATABASE_ERROR.getCode(),
                SunnyErrorCode.DATABASE_ERROR.getMessage(),
                LocalDateTime.now(),
                null
        ));
    }

    // --- 4. NULL POINTER EXCEPTION ---
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiErrorResponse> handleNpe(NullPointerException ex) {
        System.err.println("NPE Detected: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(
                SunnyErrorCode.NULL_POINTER.getCode(),
                SunnyErrorCode.NULL_POINTER.getMessage(),
                LocalDateTime.now(),
                null
        ));
    }

    // --- 5. LE FILET DE SÉCURITÉ FINAL (Toutes les autres Exception) ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponse(
                SunnyErrorCode.INTERNAL_ERROR.getCode(),
                "Une erreur imprévue est survenue.",
                LocalDateTime.now(),
                null
        ));
    }
}
