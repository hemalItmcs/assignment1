/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.customenum;

/**
 * CommonStatusEnum 2019 Filename: CommonStatusEnum.java Description: enum
 * Collection of common response status,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public enum CommonStatusEnum {
    DUPLICATEUSERNAME("DUPLICATEUSERNAME"),
    COMPLETEAUTHENTICATION("COMPLETEAUTHENTICATION"),
    SUCCESSINSERT("SUCCESSINSERT"),
    SUCCESSUPDATE("SUCCESSUPDATE"),
    WRONGTOKEN("WRONGTOKEN"),
    SUCCESSAUTHENTICATION("SUCCESSAUTHENTICATION"),
    REMAINEMAILAUTH("REMAINEMAILAUTH");
    String status;
    CommonStatusEnum(String status){
        this.status=status;
    }
    
    @Override
    public String toString(){
        return this.status;
    }
}
