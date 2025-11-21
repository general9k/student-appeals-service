package ru.ystu.studentappealsservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ServerError implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private CodeEnum code;
    private String message;
}
