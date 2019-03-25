/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest.repositoryimpl;

/**
 *
 * @author ITMCS-1
 */
import com.springboot.rest.customexception.CommonException;
import com.springboot.rest.dao.GenericDBImpl;
import com.springboot.rest.entity.ConfirmationTokenModel;
import com.springboot.rest.repository.ConfirmationTokenRepository;
import com.springboot.rest.rowmapper.ConfirmationTokenRowMapper;
import com.springboot.rest.utility.QueryGeneratorUtility;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * ConfirmationTokenRepositoryImpl 2019 Filename: ConfirmationTokenRepositoryImpl.java Description: implementation of data access layer file
 * implementaion file for database operation method for the {@link ConfirmationTokenRepository}
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@Repository
public class ConfirmationTokenRepositoryImpl extends GenericDBImpl<ConfirmationTokenModel> implements ConfirmationTokenRepository{
    
    @Override
    public ConfirmationTokenModel findById(BigInteger id) throws CommonException {
        RowMapper<ConfirmationTokenModel> rowMapper = new ConfirmationTokenRowMapper();
        Map<String,Object> var = new HashMap<>();
        var.put(QueryGeneratorUtility.getPrimaryKeyColumn(ConfirmationTokenModel.class),id);
        return jdbcTemplate.queryForObject(QueryGeneratorUtility.generateQueryForFindBy(ConfirmationTokenModel.class, var), rowMapper);
    }

    @Override
    public List<ConfirmationTokenModel> findAll() throws CommonException {
        String sql = QueryGeneratorUtility.generateQueryForFind(ConfirmationTokenModel.class);	
        RowMapper<ConfirmationTokenModel> rowMapper = new ConfirmationTokenRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void deleteById(BigInteger id) throws CommonException {
        Map<String,Object> var = new HashMap<>();
        var.put(QueryGeneratorUtility.getPrimaryKeyColumn(ConfirmationTokenModel.class),id);
        String sql = QueryGeneratorUtility.generateQueryForDeleteBy(ConfirmationTokenModel.class,var);
        jdbcTemplate.update(sql);
    }

    @Override
    public void deleteAllByIds(List<BigInteger> ids) throws CommonException {
        String sql = QueryGeneratorUtility.generateQueryForDelete(ConfirmationTokenModel.class) + "WHERE "+QueryGeneratorUtility.getPrimaryKeyColumn(ConfirmationTokenModel.class) + " IN (";
        for(BigInteger id : ids){
            sql=sql+id+",";
        }
        sql=sql.substring(0, sql.length()-1)+")";
        jdbcTemplate.update(sql);
    }

    @Override
    public void saveConfirmationToken(ConfirmationTokenModel confirmationTokenModel) throws CommonException {
       
        super.save(confirmationTokenModel);
    }

    @Override
    public ConfirmationTokenModel findByConfirmationtoken(String token) {
        try{
            RowMapper<ConfirmationTokenModel> rowMapper = new ConfirmationTokenRowMapper();
            Map<String,Object> var = new HashMap<>();
            var.put("confirmationToken",token);
            return jdbcTemplate.queryForObject(QueryGeneratorUtility.generateQueryForFindBy(ConfirmationTokenModel.class, var), rowMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteConfirmationTokenByUserId(BigInteger userId) {
        Map<String,Object> var = new HashMap<>();
        var.put("userid",userId);
        String sql = QueryGeneratorUtility.generateQueryForDeleteBy(ConfirmationTokenModel.class,var);
        jdbcTemplate.update(sql);
    }

    @Override
    public ConfirmationTokenModel findByUserId(String username) {
        try{
            String sql = "Select confirmationtokenPk,confirmationtoken,createdDate,userid from tbl_confirmationtoken where userid=(select userpk from tbl_users where username = '"+username+"')";
            RowMapper<ConfirmationTokenModel> rowMapper = new ConfirmationTokenRowMapper();
            return jdbcTemplate.queryForObject(sql, rowMapper);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void deleteConfirmationTokenByUsername(String username) {
        String sql = "delete from tbl_confirmationtoken where userid = (select userpk from tbl_users where username = '"+username+"')";
        jdbcTemplate.update(sql);
    }
    
}

