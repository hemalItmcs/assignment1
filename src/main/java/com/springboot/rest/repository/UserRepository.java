/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.repository;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.entity.UserModel;
import java.util.List;

/**
 * UserRepository 2019 Filename: UserRepository.java Description: data access layer file
 * interface file for database operation method for the {@link UserModel}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public interface UserRepository {
    public List<UserModel> getAllUser()throws CommonException;
    public UserModel getUserByUsername(String username);
    public UserModel saveUser(UserModel usermodel)throws CommonException;
    public void updateUser(UserModel usermodel)throws CommonException;
    public void deleteUserByUsername(String username);
}
