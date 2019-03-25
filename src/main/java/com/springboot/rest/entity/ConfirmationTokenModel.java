package com.springboot.rest.entity;

import com.springboot.rest.customannotation.Column;
import com.springboot.rest.customannotation.Table;
import java.math.BigInteger;
import java.util.Date;

/**
 * SpringBootRestDemo 2019 Filename: ConfirmationTokenModel.java Description:
 * Database model request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Table(name="tbl_confirmationtoken")
public class ConfirmationTokenModel {
    @Column(name="confirmationtokenPk",isPk = true)
    private BigInteger tokenid;
    @Column(name="confirmationtoken")
    private String confirmationToken;
    @Column(name="createdDate")
    private Date createdDate;
    @Column(name="userid")
    private BigInteger user;

    public BigInteger getUser() {
        return user;
    }

    public void setUser(BigInteger user) {
        this.user = user;
    }

    public BigInteger getTokenid() {
        return tokenid;
    }

    public void setTokenid(BigInteger tokenid) {
        this.tokenid = tokenid;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
