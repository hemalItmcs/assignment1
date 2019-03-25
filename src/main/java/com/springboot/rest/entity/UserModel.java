package com.springboot.rest.entity;

import com.springboot.rest.customannotation.Column;
import com.springboot.rest.customannotation.Table;
import java.math.BigInteger;

/**
 * SpringBootRestDemo 2019 Filename: UserModel.java Description: Database model
 * request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Table(name="tbl_users")
public class UserModel {
    
    @Column(name="userpk",isPk = true)
    BigInteger userId;
    @Column(name="firstname")
    String firstName;
    @Column(name="lastname")
    String lastName;
    @Column(name="username")
    String userName;
    @Column(name="password")
    String password;
    @Column(name="isverify")
    int isVerify;
    
    public UserModel(){
        //do nothing constructor
    }
    
    public UserModel(String firstName,String lastName,String userName,String password){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.password=password;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(int isVerify) {
        this.isVerify = isVerify;
    }

}
