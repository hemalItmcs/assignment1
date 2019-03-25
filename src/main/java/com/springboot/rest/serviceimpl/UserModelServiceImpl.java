/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.serviceimpl;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.entity.UserModel;
import com.springboot.rest.repository.UserRepository;
import com.springboot.rest.service.UserService;
import com.springboot.rest.utility.EncryptDecryptStringWithDES;
import com.springboot.rest.utility.UtilityService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * UserModelServiceImpl 2019 Filename: UserModelServiceImpl.java Description: Service implemetation Layer file
 * Class file implementation for the {@link UserService}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Service
public class UserModelServiceImpl implements UserService {

        @Autowired 
	UserRepository userRepository;
	
	@Autowired
	private Environment env;

        
        private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	/**
	 *  Used for get all User form database 
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
	@Override
	public List<UserModel> getAllUsers() throws CommonException{
            return userRepository.getAllUser();
	}

	/**
	 * Used for insert User form database
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
	@Override
	public synchronized UserModel saveUser(UserModel user) throws CommonException{
            user.setPassword(EncryptDecryptStringWithDES.encrypt(user.getPassword(), user.getUserName())); 
            LOGGER.info("in service save...");
            userRepository.saveUser(user);
            LOGGER.info("after service save...");
            return user;
        }
        /**
	 * Used for update User form database
	 *
	 * @author Itmusketeers
	 * @version 1.0 
	 * @Last modified 2019-03-07
	 */
	@Override
	public void updateUser(UserModel user) throws CommonException{
            userRepository.updateUser(user);
	}

	
	

	/**
	 * Method is used for authentication of user
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
	@Override
	public String authenticatUser(UserModel user)throws CommonException {
            String resposne = "INVALIDUSER";
            UserModel userModel = userRepository.getUserByUsername(user.getUserName());
            if (userModel!=null) {
                if (userModel.getIsVerify() == 1) {
                    String inputDetails = EncryptDecryptStringWithDES.decrypt(userModel.getPassword(),userModel.getUserName());
                    resposne = inputDetails.equalsIgnoreCase(user.getPassword()) ? "SUCCESSAUTHENTICATION" : "INVALIDPASSWORD";
                } else {
                    resposne = "REMAINEMAILAUTH";
                }
            }
            return resposne;
	}

    @Override
    public UserModel getUserbyUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void sendMailForConfirmationCodeToUser(String token,String username)throws CommonException {
        String url = "http://"+env.getProperty("server.host")+":"+env.getProperty("server.port")+"/user/confirm-account?token="+token;
	String confirmationtoken = "To confirm your account, please click here : <a href='"+url+"'>"+url+"</a>";
        UtilityService.sendMail(username,"Complete Registration!",confirmationtoken,env);
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }
    
}
