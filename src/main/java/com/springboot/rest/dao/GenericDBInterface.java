package com.springboot.rest.dao;

import com.springboot.rest.customexception.CommonException;
import java.math.BigInteger;
import java.util.List;

/**
 * GenericDBInterface 2019 Filename: GenericDBInterface.java Description: interface
 * Inteface file for some generic basic database operation
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
public interface GenericDBInterface<T> {
    public void save(T entity)throws CommonException;
    public void saveAll(List<T> entity)throws CommonException;
    public void update(T entity)throws CommonException;
    public void updateAll(List<T> entity)throws CommonException;
    public T findById(BigInteger id)throws CommonException;
    public List<T> findAll()throws CommonException;
    public void deleteById(BigInteger id)throws CommonException;
    public void deleteAllByIds(List<BigInteger> ids)throws CommonException;
}
