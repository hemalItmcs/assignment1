/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.service;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.entity.ConfirmationTokenModel;
import java.math.BigInteger;

/**
 * ConfirmationTokenService 2019 Filename: ConfirmationTokenService.java Description: Service Layer file
 * Interface file for the {@link ConfirmationTokenModel}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
public interface ConfirmationTokenService {
    public void saveConfiramtionToken(String token,BigInteger userId)throws CommonException;
    public ConfirmationTokenModel findByConfirmationtoken(String token)throws CommonException;
    public void deleteConfirmationTokenByUserId(BigInteger userId)throws CommonException;
    public ConfirmationTokenModel findByUsername(String username);
    public void deleteConfirmationTokenByUsername(String username);
}
