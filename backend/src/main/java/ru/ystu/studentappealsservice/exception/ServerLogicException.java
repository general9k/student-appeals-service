package ru.ystu.studentappealsservice.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class ServerLogicException extends CoreException {

    private final ServerLogicExceptionType type;
    private final String localizationKey;
    private final String localizationNamespace;
    private final Map<String, String> arguments;

    public ServerLogicException(final ServerLogicExceptionType type) {
        super(type.getReplacePattern() == null ? type.getMessage() : type.getMessage().replace(type.getReplacePattern(), ""));
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final ServerLogicExceptionType type, String localizationKey, String localizationNamespace) {
        super(type.getReplacePattern() == null ? type.getMessage() : type.getMessage().replace(type.getReplacePattern(), ""));
        this.type = type;
        this.localizationKey = localizationKey;
        this.localizationNamespace = localizationNamespace;
        this.arguments = null;
    }

    public ServerLogicException(final ServerLogicExceptionType type, final Throwable cause) {
        super(type.getReplacePattern() == null ? type.getMessage() : type.getMessage().replace(type.getReplacePattern(), ""), cause);
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final ServerLogicExceptionType type, final String object) {
        super(type.getMessage().replace(type.getReplacePattern(), object));
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final ServerLogicExceptionType type, final String object, final Throwable cause) {
        super(type.getMessage().replace(type.getReplacePattern(), object), cause);
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final String message, final ServerLogicExceptionType type) {
        super(message);
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final String message, final ServerLogicExceptionType type, final Throwable cause) {
        super(message, cause);
        this.type = type;
        this.localizationKey = null;
        this.localizationNamespace = null;
        this.arguments = null;
    }

    public ServerLogicException(final String message, final ServerLogicExceptionType type, final Throwable cause,
                                String localizationKey, String localizationNamespace) {
        super(message, cause);
        this.type = type;
        this.localizationKey = localizationKey;
        this.localizationNamespace = localizationNamespace;
        this.arguments = null;
    }

    public ServerLogicException(final String message, final ServerLogicExceptionType type,
                                String localizationKey, String localizationNamespace) {
        super(message);
        this.type = type;
        this.localizationKey = localizationKey;
        this.localizationNamespace = localizationNamespace;
        this.arguments = null;
    }

    public ServerLogicException(final String message, final ServerLogicExceptionType type,
                                String localizationKey, String localizationNamespace, Map<String, String> arguments) {
        super(message);
        this.type = type;
        this.localizationKey = localizationKey;
        this.localizationNamespace = localizationNamespace;
        this.arguments = arguments;
    }

    public ServerError toServerError() {
        return new ServerError(this.type.getErrorCode(), this.getMessage());
    }
}
