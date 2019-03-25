/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * InvalidDataException 2019 Filename: InvalidDataException.java Description: Custom Exception
 * Custom exception class for if user login with wrong username and password
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String exception) {
        super(exception);
    }
}
