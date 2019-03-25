/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.serviceimpl;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.entity.ConfirmationTokenModel;
import com.springboot.rest.repository.ConfirmationTokenRepository;
import com.springboot.rest.service.ConfirmationTokenService;
import com.springboot.rest.utility.UtilityService;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ConfirmationTokenServiceImpl 2019 Filename: ConfirmationTokenServiceImpl.java Description: Service implemetation Layer file
 * Class file implementation for the {@link ConfirmationTokenService}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    
    @Override
    public void saveConfiramtionToken(String token, BigInteger userId)throws CommonException {
        ConfirmationTokenModel confirmationTokenModel = new ConfirmationTokenModel();
        confirmationTokenModel.setConfirmationToken(token);
        confirmationTokenModel.setUser(userId);
        confirmationTokenModel.setCreatedDate(UtilityService.currentDate());
        confirmationTokenRepository.saveConfirmationToken(confirmationTokenModel);
    }

    @Override
    public ConfirmationTokenModel findByConfirmationtoken(String token) throws CommonException {
        return confirmationTokenRepository.findByConfirmationtoken(token);
    }

    @Override
    public void deleteConfirmationTokenByUserId(BigInteger userId) throws CommonException {
        confirmationTokenRepository.deleteConfirmationTokenByUserId(userId);
    }

    @Override
    public ConfirmationTokenModel findByUsername(String username) {
       return confirmationTokenRepository.findByUserId(username);
    }

    @Override
    public void deleteConfirmationTokenByUsername(String username) {
        confirmationTokenRepository.deleteConfirmationTokenByUsername(username);
    }
    
}

