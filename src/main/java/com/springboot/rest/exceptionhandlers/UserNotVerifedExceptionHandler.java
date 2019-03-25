/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.exceptionhandlers;

import com.springboot.rest.customenum.CommonStatusEnum;
import com.springboot.rest.customexception.UrlExpiredException;
import com.springboot.rest.customexception.UserNotVerifiedException;
import com.springboot.rest.utility.ResponseGeneratorService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserNotVerifedExceptionHandler 2019 Filename: UserNotVerifedExceptionHandler.java Description: custom exception handler
 * Class file for handling UserNotVerifed exception {@link UserNotVerifiedException}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@ControllerAdvice
@RestController
public class UserNotVerifedExceptionHandler {
    @ExceptionHandler(UserNotVerifiedException.class)
    public final ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotVerifiedException ex) {
        return new ResponseEntity(ResponseGeneratorService.errorResponse(ex.getMessage(),CommonStatusEnum.REMAINEMAILAUTH.toString()),HttpStatus.FORBIDDEN);
    }
}
