package com.askie01.accounts.exception;

import com.askie01.accounts.constant.ResponseCode;
import com.askie01.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        final Map<String, String> validationErrors = new HashMap<>();
        final List<ObjectError> validationErrorList = exception.getBindingResult().getAllErrors();
        validationErrorList.forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception exception,
                                                                  WebRequest request) {
        final String requestPath = request.getDescription(false);
        final Integer statusCode = ResponseCode.INTERNAL_SERVER_ERROR;
        final String errorMessage = exception.getMessage();
        final LocalDateTime timestamp = LocalDateTime.now();
        final ErrorResponseDTO response = ErrorResponseDTO.builder()
                .path(requestPath)
                .code(statusCode)
                .message(errorMessage)
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MobilePhoneAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleMobilePhoneAlreadyExistsException(MobilePhoneAlreadyExistsException exception,
                                                                                    WebRequest request) {
        final String requestPath = request.getDescription(false);
        final Integer statusCode = ResponseCode.BAD_REQUEST;
        final String errorMessage = exception.getMessage();
        final LocalDateTime timestamp = LocalDateTime.now();
        final ErrorResponseDTO response = ErrorResponseDTO.builder()
                .path(requestPath)
                .code(statusCode)
                .message(errorMessage)
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerNotFoundException(CustomerNotFoundException exception,
                                                                            WebRequest request) {
        final String requestPath = request.getDescription(false);
        final Integer statusCode = ResponseCode.NOT_FOUND;
        final String errorMessage = exception.getMessage();
        final LocalDateTime timestamp = LocalDateTime.now();
        final ErrorResponseDTO response = ErrorResponseDTO.builder()
                .path(requestPath)
                .code(statusCode)
                .message(errorMessage)
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccountNotFoundException(AccountNotFoundException exception,
                                                                           WebRequest request) {
        final String requestPath = request.getDescription(false);
        final Integer statusCode = ResponseCode.NOT_FOUND;
        final String errorMessage = exception.getMessage();
        final LocalDateTime timestamp = LocalDateTime.now();
        final ErrorResponseDTO response = ErrorResponseDTO.builder()
                .path(requestPath)
                .code(statusCode)
                .message(errorMessage)
                .timestamp(timestamp)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
