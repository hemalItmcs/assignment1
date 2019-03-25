package com.springboot.rest.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springboot.rest.entity.UserModel;

/**
 * SpringBootRestDemo 2019 Filename: UserRowMapper.java Description:
 * UserRowMapper class request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
public class UserRowMapper implements RowMapper<UserModel> {

    /**
     * Method is used for map database result set to the model file.
     *
     * @author Itmusketeers
     * @version 1.0
     * @Last modified 2019-03-07
     */
    @Override
    public UserModel mapRow(ResultSet row, int rowNum) throws SQLException {
        UserModel objUserModel = new UserModel();
        objUserModel.setUserId(new BigInteger(row.getString("userpk")));
        objUserModel.setFirstName(row.getString("firstname"));
        objUserModel.setLastName(row.getString("lastname"));
        objUserModel.setUserName(row.getString("username"));
        objUserModel.setPassword(row.getString("password"));
        objUserModel.setIsVerify(row.getInt("isverify"));
        return objUserModel;
    }

}
