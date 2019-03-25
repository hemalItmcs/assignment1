/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * CommonException 2019 Filename: CommonException.java Description: Custom Exception
 * Custom exception class for any runtime exception
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class CommonException extends RuntimeException {
    public CommonException(String s) 
    { 
        // Call constructor of parent Exception 
        super(s); 
    } 
}
