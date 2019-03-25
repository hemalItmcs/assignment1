/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * UrlExpiredException 2019 Filename: UrlExpiredException.java Description: Custom Exception
 * Custom exception class for if user try to confirm account with wrong url or wrong token
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class UrlExpiredException extends RuntimeException {
    public UrlExpiredException(String exception) {
        super(exception);
    }
}
