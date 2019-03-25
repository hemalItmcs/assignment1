package com.springboot.rest.service;

import com.springboot.rest.customexception.CommonException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.springboot.rest.entity.UserModel;

/**
 * UserService 2019 Filename: UserService.java Description: Service Layer file
 * Interface file for the {@link UserModel}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Service
public interface UserService {
    List<UserModel> getAllUsers()throws CommonException;
    UserModel saveUser(UserModel user) throws CommonException;
    void updateUser(UserModel user) throws CommonException;
    String authenticatUser(UserModel user)throws CommonException;
    UserModel getUserbyUsername(String username);
    public void sendMailForConfirmationCodeToUser(String token,String username)throws CommonException;
    public void deleteUserByUsername(String username);
}
