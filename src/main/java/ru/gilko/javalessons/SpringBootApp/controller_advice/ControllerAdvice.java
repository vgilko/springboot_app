package ru.gilko.javalessons.SpringBootApp.controller_advice;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(EmptyResultDataAccessException exception, WebRequest request) {
        log.error("Unable to find entity for request: "); // хочу выводить все параметры запроса.
        log.error("Exception: " + exception.getMessage());

        ErrorMessage message = new ErrorMessage(
                "Unable to find entity."
        );

        return message;
    }

    // обертка для BeanValidation
    // отлов всех ошибок
}
