package fr.projet.betasunny.betasunny.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
                .map(error -> "validation." + error.getField() + "." + error.getCode().toLowerCase() + " : " +  error.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                SunnyErrorCode.VALIDATION_ERROR.getCode(),
                SunnyErrorCode.VALIDATION_ERROR.getMessage(),
                LocalDateTime.now(),
                validationKeys
        ));
    }

    //  : GESTION DES ERREURS MÉTIERS (ex: SPOT_NOT_FOUND) ---
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessResourceNotFound(ResourceNotFoundException ex) {

        SunnyErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                LocalDateTime.now(),
                null
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
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseError(DataAccessException ex) {
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

    // --- 6. ERREURS DE VALIDATION SUR LES PARAMÈTRES (ex: @PathVariable, @RequestParam) ---
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                SunnyErrorCode.VALIDATION_ERROR.getCode(),
                "Erreur de validation des paramètres",
                java.time.LocalDateTime.now(),
                errors
        ));
    }

    // --- 7. PARAMÈTRE MANQUANT (SB-REQ-401) ---
    // Ex: Oubli d'un @RequestParam obligatoire
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingParam(MissingServletRequestParameterException ex) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                SunnyErrorCode.MISSING_PARAMETER.getCode(),
                "Le paramètre suivant est absent : " + ex.getParameterName(),
                LocalDateTime.now(),
                null
        ));
    }

    // --- 8. JSON MALFORMÉ (SB-REQ-402) ---
    // Ex: Une virgule en trop ou un format de date invalide dans le JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedJson(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ApiErrorResponse(
                SunnyErrorCode.MALFORMED_JSON.getCode(),
                SunnyErrorCode.MALFORMED_JSON.getMessage(),
                LocalDateTime.now(),
                null
        ));
    }

    // --- 9. MAUVAISE MÉTHODE HTTP (SB-REQ-405) ---
    // Ex: Faire un POST sur une route qui n'accepte que du GET
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodNotSupported(org.springframework.web.HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiErrorResponse(
                SunnyErrorCode.METHOD_NOT_SUPPORTED.getCode(),
                "La méthode " + ex.getMethod() + " n'est pas autorisée pour cette URL.",
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
