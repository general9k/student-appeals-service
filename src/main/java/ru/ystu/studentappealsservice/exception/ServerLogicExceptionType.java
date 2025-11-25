package ru.ystu.studentappealsservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import ru.ystu.studentappealsservice.util.Constants;

@Getter
@AllArgsConstructor
public enum ServerLogicExceptionType {
    VALIDATION_ERROR(CodeEnum.VALIDATION_ERROR, HttpStatus.BAD_REQUEST,
            String.format("Параметры: %s не могут быть пустыми; некорректный тип данных в параметре %s",
                    Constants.FIELD_PATTERN, Constants.FIELD_PATTERN), Constants.FIELD_PATTERN),
    AUTHENTICATION_ERROR(CodeEnum.AUTHENTICATION_ERROR, HttpStatus.UNAUTHORIZED,
            String.format("Ошибка в токене: %s", Constants.OBJECT_PATTERN), Constants.OBJECT_PATTERN),
    FORBIDDEN_ERROR(CodeEnum.ACCESS_DENIED, HttpStatus.FORBIDDEN, "Недостаточно прав", Constants.REASON_PATTERN),
    NOT_FOUND(CodeEnum.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND,
            String.format("Объект %s не найден", Constants.OBJECT_PATTERN), Constants.OBJECT_PATTERN),
    ALREADY_EXISTS(CodeEnum.ENTITY_ALREADY_EXISTS, HttpStatus.CONFLICT,
            String.format("Объект %s уже существует", Constants.OBJECT_PATTERN), Constants.OBJECT_PATTERN),

    DATABASE_ERROR(CodeEnum.DATABASE_REQUEST_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Ошибка при обращении к базе: %s", Constants.REASON_PATTERN), Constants.REASON_PATTERN),
    SERVICE_ERROR(CodeEnum.SERVICE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR,
            String.format("Внутренняя ошибка сервиса: %s", Constants.REASON_PATTERN), Constants.REASON_PATTERN);

    private final CodeEnum errorCode;
    private final HttpStatus httpCode;
    private final String message;
    private final String replacePattern;
}
