/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.exceptionhandlers;

import com.springboot.rest.customenum.CommonMessageEnum;
import com.springboot.rest.customexception.InvalidDataException;
import com.springboot.rest.utility.ResponseGeneratorService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * InvalidDataExceptionHandler 2019 Filename: InvalidDataExceptionHandler.java Description: custom exception handler
 * Class file for handling InvalidData exception {@link InvalidDataException}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@ControllerAdvice
@RestController
public class InvalidDataExceptionHandler {
    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Map<String, Object>> handleUserNotFoundException(InvalidDataException ex) {
        
        return new ResponseEntity(ResponseGeneratorService.errorResponse(CommonMessageEnum.DATAINVALID.toString(),ex.getMessage()), HttpStatus.FORBIDDEN);
    }
    
}
