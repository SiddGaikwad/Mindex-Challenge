package com.mindex.challenge.controller;

import com.mindex.challenge.data.ChallengeError;
import com.mindex.challenge.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ChallengeError> mapException(ServiceException e){
        ChallengeError er= new ChallengeError(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
        return new ResponseEntity<ChallengeError>(er,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
