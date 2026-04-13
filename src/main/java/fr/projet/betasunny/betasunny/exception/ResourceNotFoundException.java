package fr.projet.betasunny.betasunny.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final SunnyErrorCode errorCode;

    public ResourceNotFoundException(SunnyErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public SunnyErrorCode getErrorCode() {
        return errorCode;
    }
}