package com.dpilaloa.api.clients.movements.exception.handler;

import com.dpilaloa.api.clients.movements.helper.ErrorResponseHelper;
import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import com.dpilaloa.api.clients.movements.util.Constants;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Slf4j
public class R2dbcDataIntegrityViolationExceptionHandler {

    public static ResponseEntity<ErrorList> handle(R2dbcDataIntegrityViolationException ex) {
        ErrorList errorList = new ErrorList();
        errorList.setCode(HttpStatus.CONFLICT.toString());
        errorList.setBusinessMessage(Constants.INVALID_DATA_INTEGRITY);
        errorList.setMessage(ex.getMessage());
        log.error("R2dbcDataIntegrityViolationException: {}",ex.getMessage());
        return ErrorResponseHelper.buildErrorResponse(errorList, HttpStatus.CONFLICT);
    }

}
