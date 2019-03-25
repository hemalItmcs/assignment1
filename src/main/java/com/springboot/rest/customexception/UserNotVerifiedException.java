/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customexception;

/**
 * UserNotVerifiedException 2019 Filename: UserNotVerifiedException.java Description: Custom Exception
 * Custom exception class for if user try to save user with same username and that old user is not verified
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public class UserNotVerifiedException extends RuntimeException  {
    public UserNotVerifiedException(String exception) {
        super(exception);
    }
}
