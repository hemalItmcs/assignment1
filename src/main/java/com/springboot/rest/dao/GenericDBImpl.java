package com.springboot.rest.dao;

import com.springboot.rest.customexception.CommonException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.springboot.rest.utility.QueryGeneratorUtility;

/**
 * GenericDBImpl 2019 Filename: GenericDBImpl.java Description: Abstract Class
 * it implements generic interface and also given default implementations
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public abstract class GenericDBImpl<T> implements GenericDBInterface<T> {
	
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public void save(T entity) throws CommonException{
        jdbcTemplate.update(QueryGeneratorUtility.generateQueryForInsert(entity));
    }

    @Override
    public void saveAll(List<T> entity) throws CommonException{
        String[] sqlArr = new String[entity.size()]; 
        int i = 0;
        for(T e : entity){
            sqlArr[i] = QueryGeneratorUtility.generateQueryForInsert(e);
        }
        jdbcTemplate.batchUpdate(sqlArr);
    }

    @Override
    public void update(T entity)throws CommonException {
        jdbcTemplate.update(QueryGeneratorUtility.generateQueryForUpdate(entity));
    }

    @Override
    public void updateAll(List<T> entity)throws CommonException {
        String[] sqlArr = new String[entity.size()]; 
        int i = 0;
        for(T e : entity){
            sqlArr[i] = QueryGeneratorUtility.generateQueryForUpdate(e);
        }	
        jdbcTemplate.batchUpdate(sqlArr);
    }
}
