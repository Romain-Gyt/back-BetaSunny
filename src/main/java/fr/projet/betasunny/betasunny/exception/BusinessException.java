package fr.projet.betasunny.betasunny.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final SunnyErrorCode errorCode;

    public BusinessException(SunnyErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}