/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.repository;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.entity.ConfirmationTokenModel;
import java.math.BigInteger;

/**
 * ConfirmationTokenRepository 2019 Filename: ConfirmationTokenRepository.java Description: data access layer file
 * interface file for database operation method for the {@link ConfirmationTokenModel}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public interface ConfirmationTokenRepository {
    public void saveConfirmationToken(ConfirmationTokenModel confirmationTokenModel)throws CommonException;
    public ConfirmationTokenModel findByConfirmationtoken(String token);
    public void deleteConfirmationTokenByUserId(BigInteger userId);
    public ConfirmationTokenModel findByUserId(String username);
    public void deleteConfirmationTokenByUsername(String username);
}
