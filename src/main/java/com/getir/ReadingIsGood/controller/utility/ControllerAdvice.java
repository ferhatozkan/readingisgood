package com.getir.ReadingIsGood.controller.utility;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleItemNotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder().status(HttpStatus.BAD_REQUEST.value())
                .errors(List.of(ErrorDetail.builder()
                .title(exception.getMessage())
                .type(exception.getClass().getSimpleName()).build())).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleValidationException(ex.getBindingResult(), ex.getClass().getSimpleName());
    }

    private ResponseEntity<ErrorResponse> handleValidationException(BindingResult bindingResult, String simpleName) {
        List<ErrorDetail> errorDetails = bindingResult.getAllErrors().stream().map(item -> ErrorDetail.builder()
                .title(item.getObjectName() + ": " + item.getDefaultMessage())
                .type(simpleName)
                .build()).collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errors(errorDetails).status(HttpStatus.BAD_REQUEST.value()).build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
