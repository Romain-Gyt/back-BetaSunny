package fr.projet.betasunny.betasunny.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SunnyErrorCode {
    // === ERREURS GÉNÉRIQUES (Problèmes Serveur) ===
    INTERNAL_ERROR("SB-GEN-500", "Une erreur interne inattendue est survenue sur nos serveurs."),
    RESOURCE_NOT_FOUND( "SB-GEN-404", "La ressource n'a pas été trouvée "),
    DATABASE_ERROR("SB-GEN-501", "Erreur de communication avec la base de données."),
    NULL_POINTER("SB-GEN-502", "Erreur de traitement des données (valeur nulle inattendue)."),

    // === ERREURS DE REQUÊTE & VALIDATION (Problèmes Client) ===
    VALIDATION_ERROR("SB-REQ-400", "Les données fournies dans le formulaire sont invalides."),
    MISSING_PARAMETER("SB-REQ-401", "Un paramètre obligatoire est manquant dans la requête."),
    MALFORMED_JSON("SB-REQ-402", "Le format du JSON envoyé est incorrect ou illisible."),
    METHOD_NOT_SUPPORTED("SB-REQ-405", "La méthode HTTP utilisée (GET/POST/PUT/DELETE) n'est pas supportée sur cette route."),

    // === ERREURS DE SÉCURITÉ & AUTH (Pour quand on réactivera Spring Security) ===
    UNAUTHORIZED("SB-SEC-401", "Vous devez être connecté pour accéder à cette ressource."),
    FORBIDDEN("SB-SEC-403", "Vous n'avez pas les droits ou le rôle nécessaire pour effectuer cette action."),
    BAD_CREDENTIALS("SB-SEC-404", "Identifiant ou mot de passe incorrect."),
    TOKEN_EXPIRED("SB-SEC-405", "Votre session a expiré, veuillez vous reconnecter."),

    // === ERREURS MÉTIERS : SPOTS ===
    SPOT_NOT_FOUND("SB-SPOT-404", "Ce spot de grimpe n'existe pas ou a été supprimé."),
    SPOT_ALREADY_EXISTS("SB-SPOT-409", "Un spot avec ce nom ou ces coordonnées existe déjà."),
    SPOT_INVALID_COORDINATES("SB-SPOT-422", "Les coordonnées GPS (latitude/longitude) sont hors limites."),

    // === ERREURS MÉTIERS : UTILISATEURS (Pour l'évolution future du projet) ===
    USER_NOT_FOUND("SB-USER-404", "L'utilisateur demandé est introuvable."),
    USER_ALREADY_EXISTS("SB-USER-409", "Un compte existe déjà avec cette adresse email.");
    private final String code;
    private final String message;
}