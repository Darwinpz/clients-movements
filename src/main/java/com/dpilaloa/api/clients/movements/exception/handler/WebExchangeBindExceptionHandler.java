package com.dpilaloa.api.clients.movements.exception.handler;

import com.dpilaloa.api.clients.movements.helper.ErrorResponseHelper;
import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WebExchangeBindExceptionHandler {

    public static ResponseEntity<List<ErrorList>> handle(WebExchangeBindException ex) {
        List<ErrorList> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorList errorList = new ErrorList();
            errorList.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            errorList.setMessage(error.getDefaultMessage());
            errorList.setBusinessMessage(Constants.INVALID_INPUT);
            errors.add(errorList);
        });
        log.error("WebExchangeBindException: {}",ex.getMessage());
        return ErrorResponseHelper.buildValidationErrorResponse(errors);
    }

}
