package ru.ystu.studentappealsservice.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeEnum {
    VALIDATION_ERROR("VALIDATION_ERROR"),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR"),
    ACCESS_DENIED("ACCESS_DENIED"),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND"),
    ENTITY_ALREADY_EXISTS("ENTITY_ALREADY_EXISTS"),
    DATABASE_REQUEST_EXCEPTION("DATABASE_REQUEST_EXCEPTION"),
    SERVICE_ERROR("SERVICE_ERROR"),
    NOT_ACCEPTABLE("NOT_ACCEPTABLE");

    private final String value;

    CodeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    @JsonCreator
    public static CodeEnum fromValue(String value) {
        CodeEnum[] var1 = values();

        for (CodeEnum b : var1) {
            if (b.value.equals(value)) {
                return b;
            }
        }

        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}