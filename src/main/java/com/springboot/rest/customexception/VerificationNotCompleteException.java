/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * VerificationNotCompleteException 2019 Filename: VerificationNotCompleteException.java Description: Custom Exception
 * Custom exception class for if user try to login with unverified username password or without confirm his/her account
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class VerificationNotCompleteException extends RuntimeException{
    public VerificationNotCompleteException(String exception) {
        super(exception);
    }
}
