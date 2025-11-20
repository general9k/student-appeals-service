package ru.ystu.studentappealsservice.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CoreExceptionAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(ServerLogicException.class)
    ResponseEntity<ServerError> handleServerLogicException(final ServerLogicException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(e).message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        // Отсутствие / невалидность параметров в request в body
    ResponseEntity<ServerError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);

        StringBuilder errorMessage = new StringBuilder();

        String emptyFields = e.getFieldErrors().stream()
                .filter(error -> ErrorConstants.NOT_NULL_CODE.equals(error.getCode()))
                .map(FieldError::getField)
                .collect(Collectors.joining(ErrorConstants.SEMICOLON_DELIMITER));
        Locale locale = LocaleContextHolder.getLocale();
        List<String> invalidTypeFields = e.getFieldErrors().stream()
                .filter(error -> !ErrorConstants.NOT_NULL_CODE.equals(error.getCode()))
                .map(error -> getInvalidArgumentMessage(error, locale))
                .toList();
        if (!emptyFields.isEmpty())
            errorMessage.append(messageSource.getMessage(ErrorConstants.FORMAT_ARGUMENTS_IS_EMPTY, new String[]{emptyFields}, locale));
        if (!invalidTypeFields.isEmpty()) {
            errorMessage.append(messageSource.getMessage(invalidTypeFields.size() == 1
                            ? ErrorConstants.FORMAT_ARGUMENT_IS_INVALID : ErrorConstants.FORMAT_ARGUMENTS_IS_INVALID,
                    new String[]{String.join(ErrorConstants.SEMICOLON_DELIMITER, invalidTypeFields)}, locale));
        }
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                .message(!errorMessage.isEmpty() ? errorMessage.toString() : e.getMessage())
                .build();
    }

    private String getInvalidArgumentMessage(FieldError error, Locale locale) {
        try {
            var message = messageSource.getMessage(error, locale);
            if (!message.isEmpty()) {
                return ErrorConstants.FORMATTER.formatted(error.getField(), messageSource.getMessage(error, locale));
            }
        } catch (Exception ignored) {
        }
        return error.getField();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ServerError> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                .message(messageSource.getMessage(ErrorConstants.FORMAT_ARGUMENTS_TYPE_NOT_VALID, new String[]{e.getName()},
                        LocaleContextHolder.getLocale())).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ServerError> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();
        String msg = e.getMessage();
        boolean isEnum = false;
        if (cause instanceof JsonMappingException jsonEx) {
            List<String> fields = jsonEx.getPath()
                    .stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .filter(Objects::nonNull)
                    .toList();
            if (cause instanceof ValueInstantiationException vie) {
                if (vie.getType().isEnumType()) {
                    msg = messageSource.getMessage(fields.size() == 1
                                    ? ErrorConstants.FORMAT_ARGUMENT_IS_INVALID : ErrorConstants.FORMAT_ARGUMENTS_IS_INVALID,
                            new String[]{String.join(ErrorConstants.COMMA_DELIMITER, fields)}, LocaleContextHolder.getLocale());
                    isEnum = true;
                }
            }
            if (!isEnum) {
                msg = messageSource.getMessage(fields.size() == 1
                                ? ErrorConstants.FORMAT_ARGUMENT_TYPE_NOT_VALID : ErrorConstants.FORMAT_ARGUMENTS_TYPE_NOT_VALID,
                        new String[]{String.join(ErrorConstants.COMMA_DELIMITER, fields)}, LocaleContextHolder.getLocale());
            }
        }
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                .message(msg).build();

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
        // Отсутствие параметра в header
    ResponseEntity<ServerError> handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.AUTHENTICATION_ERROR)
                .message(messageSource.getMessage(ErrorConstants.EXCEPTION_AUTHENTICATION, ErrorConstants.EMPTY_ARRAY, LocaleContextHolder.getLocale()))
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
        // Отсутствие параметра в request в path
    ResponseEntity<ServerError> handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                .message(messageSource.getMessage(ErrorConstants.FORMAT_ARGUMENTS_IS_EMPTY, new String[]{e.getParameterName()},
                        LocaleContextHolder.getLocale())).build();
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    ResponseEntity<ServerError> handleServletRequestBindingException(final ServletRequestBindingException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR).message(e.getMessage()).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ServerError> handleConstraintViolationException(final ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR).message(e.getMessage()).build();
    }


    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    ResponseEntity<ServerError> handleInvalidDataAccessApiUsageException(final InvalidDataAccessApiUsageException e) {
        if (e.getMessage().contains(ErrorConstants.SORT_PARAMETER_SYNTAX_ERROR)) {
            return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                    .message(messageSource.getMessage(
                            ErrorConstants.FORMAT_ARGUMENT_TYPE_NOT_VALID,
                            new String[]{ErrorConstants.SORT_VALUE},
                            LocaleContextHolder.getLocale()))
                    .build();
        }
        return handleThrowable(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ServerError> handleIllegalArgumentException(final IllegalArgumentException e) {
        if (e.getMessage().contains(ErrorConstants.SORT_DIRECTION_VALUE_SYNTAX_ERROR)) {
            return new ServerErrorBuilder(ServerLogicExceptionType.VALIDATION_ERROR)
                    .message(messageSource.getMessage(
                            ErrorConstants.FORMAT_ARGUMENT_IS_INVALID,
                            new String[]{ErrorConstants.SORT_VALUE},
                            LocaleContextHolder.getLocale()))
                    .build();
        }
        return handleThrowable(e);
    }

    @ExceptionHandler({Throwable.class})
    ResponseEntity<ServerError> handleThrowable(final Throwable e) {
        log.error(e.getMessage(), e);
        return new ServerErrorBuilder(ServerLogicExceptionType.SERVICE_ERROR)
                .message(messageSource.getMessage(ErrorConstants.UNKNOWN_ERROR, ErrorConstants.EMPTY_ARRAY, LocaleContextHolder.getLocale()))
                .build();
    }
}
