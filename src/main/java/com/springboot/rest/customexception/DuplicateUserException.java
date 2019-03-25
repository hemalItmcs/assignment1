/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * DuplicateUserException 2019 Filename: DuplicateUserException.java Description: Custom Exception
 * Custom exception class for if user saves user with same username
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String exception) {
        super(exception);
    }
}
