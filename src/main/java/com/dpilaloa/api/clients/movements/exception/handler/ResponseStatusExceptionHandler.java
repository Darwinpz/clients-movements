package com.dpilaloa.api.clients.movements.exception.handler;

import com.dpilaloa.api.clients.movements.helper.ErrorResponseHelper;
import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class ResponseStatusExceptionHandler {

    public static ResponseEntity<ErrorList> handle(ResponseStatusException ex){
        ErrorList errorList = new ErrorList();
        errorList.setCode(HttpStatus.BAD_REQUEST.toString());
        errorList.setBusinessMessage(Constants.INVALID_URL);
        errorList.setMessage(ex.getMessage());
        log.error("ResponseStatusException: {}",ex.getMessage());
        return ErrorResponseHelper.buildErrorResponse(errorList, HttpStatus.BAD_REQUEST);
    }

}
