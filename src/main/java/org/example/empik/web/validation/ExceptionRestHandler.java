package org.example.empik.web.validation;

import lombok.extern.slf4j.Slf4j;
import org.example.empik.model.dto.exception.UnsuccessfulResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.ResourceBundle;

@Slf4j
@RestControllerAdvice
public class ExceptionRestHandler {

    public static final String INVALID_OPERATION = "validation.invalid.operation";

    @Order(2)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<UnsuccessfulResponse> handleInputHttpMessageParseException(final HttpMessageNotReadableException e) {
        log.debug("Caught HttpMessageNotReadableException", e);
        final UnsuccessfulResponse response = new UnsuccessfulResponse()
                .setMessage(getMessage(INVALID_OPERATION));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Order(3)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UnsuccessfulResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.debug("Caught MethodArgumentNotValidException", e);

        final UnsuccessfulResponse response = new UnsuccessfulResponse()
                .setMessage(getMessage(INVALID_OPERATION));


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Order(4)
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<UnsuccessfulResponse> handleRestClientException(final RestClientException e){
        UnsuccessfulResponse response = new UnsuccessfulResponse()
                .setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Order(990)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<UnsuccessfulResponse> handleRuntimeException(final RuntimeException e) {
        log.error("Caught RuntimeException", e);

        final UnsuccessfulResponse response = new UnsuccessfulResponse()
                .setMessage(getMessage(INVALID_OPERATION));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Order(999)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UnsuccessfulResponse> handleException(final Exception e) {
        log.error("Caught Exception", e);

        final UnsuccessfulResponse response = new UnsuccessfulResponse()
                .setMessage(getMessage(INVALID_OPERATION));

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getMessage(String validationMessage) {
        ResourceBundle bundle = ResourceBundle.getBundle("Messages");
        return bundle.getString(validationMessage);
    }
}
