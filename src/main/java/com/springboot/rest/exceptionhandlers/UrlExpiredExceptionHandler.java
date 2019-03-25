/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.exceptionhandlers;

import com.springboot.rest.customenum.CommonStatusEnum;
import com.springboot.rest.customexception.UrlExpiredException;
import com.springboot.rest.utility.ResponseGeneratorService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * UrlExpiredExceptionHandler 2019 Filename: UrlExpiredExceptionHandler.java Description: custom exception handler
 * Class file for handling UrlExpired exception {@link UrlExpiredException}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@ControllerAdvice
@RestController
public class UrlExpiredExceptionHandler {
    @ExceptionHandler(UrlExpiredException.class)
    public final ResponseEntity<Map<String, Object>> handleUserNotFoundException(UrlExpiredException ex) {
        return new ResponseEntity(ResponseGeneratorService.errorResponse(ex.getMessage(),CommonStatusEnum.WRONGTOKEN.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
