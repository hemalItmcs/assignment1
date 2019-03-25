/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customenum;

/**
 * CommonMessageEnum 2019 Filename: CommonMessageEnum.java Description: enum
 * Collection of common response messages,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public enum CommonMessageEnum {
    USEREXIST("User already Exist"),
    SENDLINK("Already send link to the Registed email id "),
    CLICKONLINK("Please click on authentication link which sent on registered email "),
    INTERNALSERVERERROR("Some Technical Error Occured"),
    NOUSERFOUND("There is no user exist"),
    ALLUSERFETCH("All Users Fetch Successfully"),
    SUCCESSVERIFIED("You have successfully verified your email address"),
    URLEXPIRED("Your url is expired or wrong"),
    SUCCESSLOGIN("You are successfully login to your account"),
    COMPLETEEMAILVERIFIED("Please first complete your email verification"),
    DATAINVALID("Your requested data is invalid, Please check errorCode");
    String message;
    CommonMessageEnum(String message){
        this.message=message;
    }
    
    @Override
    public String toString(){
        return this.message;
    }
}
