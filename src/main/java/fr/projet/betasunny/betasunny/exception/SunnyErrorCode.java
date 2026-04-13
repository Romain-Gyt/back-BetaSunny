package fr.projet.betasunny.betasunny.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SunnyErrorCode {
    RESOURCE_NOT_FOUND("SB-404", "La ressource demandée est introuvable."),
    VALIDATION_ERROR("SB-400", "Les données fournies sont invalides."),
    INTERNAL_ERROR("SB-500", "Une erreur interne est survenue sur nos serveurs."),
    DATABASE_ERROR("SB-501", "Erreur de communication avec la base de données."),
    NULL_POINTER("SB-502", "Une erreur de traitement interne est survenue (NPE).");

    private final String code;
    private final String message;
}