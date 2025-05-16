package com.dpilaloa.api.clients.movements.helper;

import com.dpilaloa.api.clients.movements.service.models.ErrorList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


public class ErrorResponseHelper {

    public static ResponseEntity<ErrorList> buildErrorResponse(ErrorList errorList, HttpStatus status){
        return ResponseEntity.status(status).body(errorList);
    }

    public static ResponseEntity<List<ErrorList>> buildValidationErrorResponse(List<ErrorList> errors){
        return ResponseEntity.badRequest().body(errors);
    }

}
