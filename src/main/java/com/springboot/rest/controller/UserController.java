package com.springboot.rest.controller;

import com.springboot.rest.customenum.CommonMessageEnum;
import com.springboot.rest.customenum.CommonStatusEnum;
import com.springboot.rest.customexception.DuplicateUserException;
import com.springboot.rest.customexception.InvalidDataException;
import com.springboot.rest.customexception.UrlExpiredException;
import com.springboot.rest.customexception.UserNotVerifiedException;
import com.springboot.rest.customexception.VerificationNotCompleteException;
import com.springboot.rest.entity.ConfirmationTokenModel;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.rest.entity.UserModel;
import com.springboot.rest.service.ConfirmationTokenService;
import com.springboot.rest.service.UserService;
import com.springboot.rest.utility.ResponseGeneratorService;
import com.springboot.rest.utility.UtilityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SpringBootRestDemo 2019 Filename: UserController.java Description: Controller class
 * request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confiramtionTokenService;
    
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    
    
    /**
     * Save Data and User model used as a parameter where some important details are required,
     * like  firstname, lastname, username, password.
     * @author Itmusketeers
     * @version 1.0
     * @param userModel it takes {@link com.springboot.rest.entity.UserModel} as a parameter
     * @return ResponseEntity
     * @Last modified 2019-03-07
    */
    @PostMapping("save")
    public ResponseEntity<Void> saveUser(@RequestBody UserModel userModel) {
        
        LOGGER.info("hii in save user");
        LOGGER.info(userModel.getFirstName());
        UserModel user = userService.getUserbyUsername(userModel.getUserName());
        if (user != null) {
            if (user.getIsVerify() == 0) {
                throw new UserNotVerifiedException(CommonMessageEnum.SENDLINK.toString() + user.getUserName());
            } else {
                throw new DuplicateUserException(CommonMessageEnum.USEREXIST.toString());
            }
        } else {
            LOGGER.info("Hi in save");
            userService.saveUser(userModel);
            LOGGER.info("After save");
            userModel = userService.getUserbyUsername(userModel.getUserName());
            LOGGER.info("fetch user"+userModel);
            String token = UtilityService.generateConfiramtionCode();
            LOGGER.info("saving confirmation token"+userModel);
            confiramtionTokenService.saveConfiramtionToken(token, userModel.getUserId());
            userService.sendMailForConfirmationCodeToUser(token, userModel.getUserName());
            return new ResponseEntity(ResponseGeneratorService.successResponse(CommonMessageEnum.CLICKONLINK.toString() + userModel.getUserName(), CommonStatusEnum.SUCCESSINSERT.toString()), HttpStatus.CREATED);
        }
        
    }

    /**
     * Used for fetch all data about users
     * 
     * @author Itmusketeers
     * @version 1.0
     * @return ResponseEntity
     * @Last modified 2019-03-07
    */
    @GetMapping("fetchAll")
    public ResponseEntity getAllUsers() {
        List<UserModel> list = userService.getAllUsers();
        String resposneMsg = list.isEmpty() ? CommonMessageEnum.NOUSERFOUND.toString() : CommonMessageEnum.ALLUSERFETCH.toString();
        return new ResponseEntity(ResponseGeneratorService.successResponse(resposneMsg, list), HttpStatus.OK);
    }

	/**
     * After Successfully sign up this method is used for authenticate the token and update the user
     * 
     * @author Itmusketeers
     * @version 1.0
     * @param request
     * @return ResponseEntity
     * @Last modified 2019-03-07
     */
    @GetMapping("confirm-account")
    public ResponseEntity<Void> confirmAccount(HttpServletRequest request) {
        ConfirmationTokenModel confirmationTokenModel = confiramtionTokenService.findByConfirmationtoken(request.getParameter("token"));
        if (confirmationTokenModel != null) {
            UserModel userModel = new UserModel();
            userModel.setIsVerify(1);
            userModel.setUserId(confirmationTokenModel.getUser());
            userService.updateUser(userModel);
            confiramtionTokenService.deleteConfirmationTokenByUserId(confirmationTokenModel.getUser());
            return new ResponseEntity(ResponseGeneratorService.successResponse(CommonMessageEnum.SUCCESSVERIFIED.toString(), CommonStatusEnum.SUCCESSUPDATE.toString()), HttpStatus.OK);
        } else {
            throw new UrlExpiredException(CommonMessageEnum.URLEXPIRED.toString());
        }
    }
	
	/**
     * Login mehtod and User model used as a parameter where some important details are required,
     * like  username, password.
     * 
     * @author Itmusketeers
     * @version 1.0
     * @param userModel it takes {@link com.springboot.rest.entity.UserModel} as a parameter
     * @return ResponseEntity
     * @Last modified 2019-03-07
     */
    @PostMapping("login")
    public ResponseEntity<Void> checkLogin(@RequestBody UserModel userModel) {
        String resposne = userService.authenticatUser(userModel);
        if (CommonStatusEnum.SUCCESSAUTHENTICATION.toString().equalsIgnoreCase(resposne)) {
            return new ResponseEntity(ResponseGeneratorService.successResponse(CommonMessageEnum.SUCCESSLOGIN.toString(), resposne), HttpStatus.OK);
        } else if (CommonStatusEnum.REMAINEMAILAUTH.toString().equalsIgnoreCase(resposne)) {
            throw new VerificationNotCompleteException(CommonMessageEnum.COMPLETEEMAILVERIFIED.toString());
        } else {
            throw new InvalidDataException(resposne);
        }

    }

}
