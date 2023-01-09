package ru.practicum.ewm_main.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final DataIntegrityViolationException e) {
        return new ApiError(
                List.of(e.getClass().getName()),
                e.getLocalizedMessage(),
                "The required object was found.",
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFoundException(final NotFoundException e, WebRequest request) {
        return new ApiError(
                List.of(e.getClass().getName()),
                e.getLocalizedMessage(),
                "Object not found " + request.getDescription(false),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handleValidationException(final BadRequestException e, WebRequest request) {
        return new ApiError(
                List.of(e.getClass().getName()),
                e.getLocalizedMessage(),
                request.getDescription(false),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleInternalServerErrorException(final HttpServerErrorException.InternalServerError e, WebRequest request) {
        return new ApiError(
                List.of(e.getClass().getName()),
                e.getLocalizedMessage(),
                request.getDescription(false),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleThrowableExceptions(final Throwable e) {
        return new ApiError(
                List.of(e.getClass().getName()),
                e.getLocalizedMessage(),
                "Throwable exception",
                HttpStatus.BAD_REQUEST
        );
    }
}