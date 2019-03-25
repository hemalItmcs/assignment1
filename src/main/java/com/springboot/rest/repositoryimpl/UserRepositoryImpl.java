/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.repositoryimpl;

import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.dao.GenericDBImpl;
import com.springboot.rest.entity.UserModel;
import com.springboot.rest.repository.UserRepository;
import com.springboot.rest.rowmapper.UserRowMapper;
import com.springboot.rest.utility.QueryGeneratorUtility;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * UserRepositoryImpl 2019 Filename: UserRepositoryImpl.java Description: implementation of data access layer file
 * implementaion file for database operation method for the {@link UserRepository}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@Repository
public class UserRepositoryImpl extends GenericDBImpl<UserModel> implements UserRepository{
    
    private static final Logger LOGGER = LogManager.getLogger(UserRepository.class);
    
    
    @Override
    public UserModel findById(BigInteger id) throws CommonException {
        RowMapper<UserModel> rowMapper = new UserRowMapper();
        Map<String,Object> var = new HashMap<>();
        var.put(QueryGeneratorUtility.getPrimaryKeyColumn(UserModel.class),id);
        return jdbcTemplate.queryForObject(QueryGeneratorUtility.generateQueryForFindBy(UserModel.class, var), rowMapper);
        
    }

    @Override
    public List<UserModel> findAll() throws CommonException {
        String sql = QueryGeneratorUtility.generateQueryForFind(UserModel.class);	
        RowMapper<UserModel> rowMapper = new UserRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void deleteById(BigInteger id) throws CommonException {
        Map<String,Object> var = new HashMap<>();
        var.put(QueryGeneratorUtility.getPrimaryKeyColumn(UserModel.class),id);
        String sql = QueryGeneratorUtility.generateQueryForDeleteBy(UserModel.class,var);
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteAllByIds(List<BigInteger> ids) throws CommonException {
        String sql = QueryGeneratorUtility.generateQueryForDelete(UserModel.class) + "WHERE "+QueryGeneratorUtility.getPrimaryKeyColumn(UserModel.class) + " IN (";
        for(BigInteger id : ids){
            sql=sql+id+",";
        }
        sql=sql.substring(0, sql.length()-1)+")";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<UserModel> getAllUser() throws CommonException {
        return findAll();
    }

    @Override
    public UserModel getUserByUsername(String username) {
        try{
            RowMapper<UserModel> rowMapper = new UserRowMapper();
            Map<String,Object> var = new HashMap<>();
            var.put("username",username);
            return jdbcTemplate.queryForObject(QueryGeneratorUtility.generateQueryForFindBy(UserModel.class, var), rowMapper);
        }catch(EmptyResultDataAccessException e){
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public UserModel saveUser(UserModel usermodel) throws CommonException{
        LOGGER.info("in repository save...");
        super.save(usermodel);
        LOGGER.info("after repository save..");
        return usermodel;
    }

    @Override
    public void updateUser(UserModel usermodel) throws CommonException {
        super.update(usermodel);
    }

    @Override
    public void deleteUserByUsername(String username) {
        String sql = "delete from tbl_users where username='"+username+"'";
        jdbcTemplate.update(sql);
    }
    
}
