package com.dpilaloa.api.clients.movements.exception.handler;

import com.dpilaloa.api.clients.movements.helper.ErrorResponseHelper;
import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import com.dpilaloa.api.clients.movements.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebInputException;

@Slf4j
public class ServerWebInputExceptionHandler {

    public static ResponseEntity<ErrorList> handle(ServerWebInputException ex){
        ErrorList errorList = new ErrorList();
        errorList.setCode(HttpStatus.BAD_REQUEST.toString());
        errorList.setBusinessMessage(Constants.INVALID_DATA);
        errorList.setMessage(ex.getMessage());
        log.error("ServerWebInputException: {}",ex.getMessage());
        return ErrorResponseHelper.buildErrorResponse(errorList, HttpStatus.BAD_REQUEST);
    }

}
