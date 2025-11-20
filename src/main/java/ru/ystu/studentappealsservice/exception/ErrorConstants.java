package ru.ystu.studentappealsservice.exception;

interface ErrorConstants {

    String FORMAT_ARGUMENTS_TYPE_NOT_VALID = "ARGUMENTS_TYPE_NOT_VALID";
    String FORMAT_ARGUMENT_TYPE_NOT_VALID = "ARGUMENT_TYPE_NOT_VALID";
    String FORMAT_ARGUMENTS_IS_EMPTY = "ARGUMENTS_IS_EMPTY";
    String FORMAT_ARGUMENTS_IS_INVALID = "ARGUMENTS_IS_INVALID";
    String FORMAT_ARGUMENT_IS_INVALID = "ARGUMENT_IS_INVALID";
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    String EXCEPTION_AUTHENTICATION = "EXCEPTION_AUTHENTICATION";
    String NOT_NULL_CODE = "NotNull";
    String FORMATTER = "%s (%s)";
    String[] EMPTY_ARRAY = new String[]{};
    String SEMICOLON_DELIMITER = "; ";
    String COMMA_DELIMITER = ", ";

    String SORT_VALUE = "sort";
    String SORT_PARAMETER_SYNTAX_ERROR = "expecting one of the following tokens: <EOF>, ',', ASC, DESC, FETCH, LIMIT, NULLS, OFFSET";
    String SORT_DIRECTION_VALUE_SYNTAX_ERROR = "Sort.Direction";
}
