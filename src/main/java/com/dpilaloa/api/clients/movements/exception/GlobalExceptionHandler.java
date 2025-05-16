package com.dpilaloa.api.clients.movements.exception;

import com.dpilaloa.api.clients.movements.exception.handler.R2dbcDataIntegrityViolationExceptionHandler;
import com.dpilaloa.api.clients.movements.exception.handler.ResponseStatusExceptionHandler;
import com.dpilaloa.api.clients.movements.exception.handler.ServerWebInputExceptionHandler;
import com.dpilaloa.api.clients.movements.exception.handler.WebExchangeBindExceptionHandler;
import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<List<ErrorList>> handleWebExchangeBindExceptions(WebExchangeBindException ex) {
        return WebExchangeBindExceptionHandler.handle(ex);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorList> handleServerWebInputExceptions(ServerWebInputException ex){
       return ServerWebInputExceptionHandler.handle(ex);
    }

    @ExceptionHandler(R2dbcDataIntegrityViolationException.class)
    public ResponseEntity<ErrorList> handleR2dbcDataIntegrityViolationException(R2dbcDataIntegrityViolationException ex) {
        return R2dbcDataIntegrityViolationExceptionHandler.handle(ex);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorList> handleResponseStatusException(ResponseStatusException ex){
        return ResponseStatusExceptionHandler.handle(ex);
    }

}
