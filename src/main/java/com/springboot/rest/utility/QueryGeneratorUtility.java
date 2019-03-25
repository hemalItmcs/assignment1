package com.springboot.rest.utility;

import java.lang.reflect.Field;
import org.springframework.stereotype.Service;
import com.springboot.rest.customannotation.Column;
import com.springboot.rest.customannotation.Table;
import com.springboot.rest.customexception.CommonException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class QueryGeneratorUtility {
    
    private static final Logger LOGGER = LogManager.getLogger(EncryptDecryptStringWithDES.class);
    
    QueryGeneratorUtility(){
        //do nothing constructor
    }
	
    public static String generateQueryForInsert(Object object) throws CommonException {
        try {
            String tableName = ((Table) object.getClass().getAnnotation(Table.class)).name();
            List<Field> fields = getNonPKColumns(object.getClass());
            String fieldsQuery = "";
            String valuesQuery = "";
            List<Method> methods = getSpecificMethod(object.getClass(), "get");
            for (Field f : fields) {
                fieldsQuery = fieldsQuery +  ((Column) f.getAnnotation(Column.class)).name() + "," ;
                for (Method method : methods) {
                    if(method.getName().toLowerCase().contains(f.getName().toLowerCase())){
                        Object value =  method.invoke(object);
                        valuesQuery = valuesQuery + (value instanceof String ? "'"+value+"'" : (value instanceof Date ?  "'"+UtilityService.convertDateTOString((Date)value,"yyyy-MM-dd hh:mm:ss")+"'" :value)) + ","  ;
                    }
                }
            }
            return "INSERT INTO " + tableName + " (" + fieldsQuery.substring(0, fieldsQuery.length() - 1) + ") values (" + valuesQuery.substring(0, valuesQuery.length() - 1) + ")";
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }
    
    public static String generateQueryForFind(Class type){
        String tableName = ((Table)type.getAnnotation(Table.class)).name();
        Field[] fields = type.getDeclaredFields();
        String query = "SELECT ";
        for(Field f : fields) {
            query = query + ((Column)f.getAnnotation(Column.class)).name() +",";
        }
        return query.substring(0,query.length()-1) + " FROM "+tableName;        
    }
    
    public static String generateQueryForFindBy(Class type,Map<String,Object> values){
        return generateQueryForFind(type) + generateQueryForWhere(values);
    }
    
    public static String generateQueryForUpdate(Object object) throws CommonException {
        try {
            String tableName = ((Table) object.getClass().getAnnotation(Table.class)).name();
            List<Field> fields = getNonPKColumns(object.getClass());
            List<Method> methods = getSpecificMethod(object.getClass(), "get");
            String query = "UPDATE " + tableName + " SET " + generateQueryForUpdatedValues(fields, methods, object);
            Field f = getPKColumn(object.getClass());
            for (Method method : methods) {
                if(method.getName().toLowerCase().contains(f.getName().toLowerCase())){
                    Object value = method.invoke(object);
                    query = query +  ((Column) f.getAnnotation(Column.class)).name() + "=" + (value instanceof String ? "'"+value+"'" : (value instanceof Date ?  "'"+UtilityService.convertDateTOString((Date)value,"yyyy-MM-dd hh:mm:ss")+"'" :value))  ;
                }
            }
            return query;
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }
    
    public static String generateQueryForUpdatedValues(List<Field> fields, List<Method> methods, Object object)throws CommonException  {
        try {
            String query = "";
            for (Field f : fields) {
                for (Method method : methods) {
                    if(method.getName().toLowerCase().contains(f.getName().toLowerCase())){
                        Object value = method.invoke(object);
                        if(value!=null){
                            query = query +  ((Column) f.getAnnotation(Column.class)).name() + "=" + (value instanceof String ? "'"+value+"'" : (value instanceof Date ?  "'"+UtilityService.convertDateTOString((Date)value,"yyyy-MM-dd hh:mm:ss")+"'" :value))  + "," ;
                        }
                    }
                }
            }
            query = query.substring(0, query.length() - 1) + " WHERE ";
            return query;
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }
    
    public static List<Field> getNonPKColumns(Class type){
        Field[] fields = type.getDeclaredFields();
        List<Field> nonPkFields = new ArrayList();
        for (Field f : fields) {
            if (!((Column) f.getAnnotation(Column.class)).isPk()) {
                nonPkFields.add(f);
            }
        }
        return nonPkFields;
    }
    
    public static Field getPKColumn(Class type){
        Field[] fields = type.getDeclaredFields();
        for (Field f : fields) {
            if (((Column) f.getAnnotation(Column.class)).isPk()) {
                return f;
            }
        }
        return null;
    }
    
    public static List<Method> getSpecificMethod(Class type,String methodType){
        Method[] methods = type.getMethods();
        List<Method> typeMethods = new ArrayList();
        for (Method method : methods) {
            if (method.getName().toLowerCase().contains(methodType.toLowerCase())) {
                typeMethods.add(method);
            }
        }
        return typeMethods;
    }
    
    public static String generateQueryForDelete(Class type){
        return "DELETE FROM "+((Table)type.getAnnotation(Table.class)).name();
    }
     
    public static String generateQueryForDeleteBy(Class type,Map<String,Object> values){
        return generateQueryForDelete(type) + generateQueryForWhere(values);
    }
    
    public static String generateQueryForWhere(Map<String,Object> values){
        String where = " WHERE ";
        Iterator itr = values.keySet().iterator();
        while(itr.hasNext()){
            String key = (String) itr.next();
            if(values.get(key) instanceof String){
                where = where + key + "= '" + values.get(key) + "' ,";
            }else{
                where = where + key + "=" + values.get(key) + ",";
            }
        }
        return where.substring(0,where.length()-1);
    }
    
    public static String getPrimaryKeyColumn(Class type){
        Field[] fields = type.getDeclaredFields();
        for(Field f : fields) {
            if(((Column)f.getAnnotation(Column.class)).isPk()) {
                return ((Column)f.getAnnotation(Column.class)).name();
            }
        }
        return null;
    }
	
    
}
