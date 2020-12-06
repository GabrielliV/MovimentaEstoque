package com.gabrielli.controla_estoque.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EstoqueException.class)
    public ResponseEntity<ErrorResponse> handleThrowableEstoque(EstoqueException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ProdutoException.class)
    public ResponseEntity<ErrorResponse> handleThrowableProduto(ProdutoException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
