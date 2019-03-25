package com.springboot.rest.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * SpringBootRestDemo 2019 Filename: ResponseGeneratorService.java Description:
 * ResponseGeneratorService class request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
@Service
public class ResponseGeneratorService {

    /**
     * Make error response with status, error message and error object and error
     * message and error object is required
     *
     * @author Itmusketeers
     * @version 1.0
     * @Last modified 2019-03-07
     */
    
    ResponseGeneratorService(){
        //do nothing constructor
    }
    
    public static Map<String, Object> errorResponse(Object errorMessage, Object error) {
        Map<String, Object> map = new HashMap();
        map.put("isSuccess", 0);
        map.put("errorMessage", errorMessage);
        map.put("errorCode", error);
        return map;
    }

    /**
     * Make error response with status, error message and error object and error
     * message and error object is required
     *
     * @author Itmusketeers
     * @version 1.0
     * @Last modified 2019-03-07
     */
    public static Map<String, Object> successResponse(Object successMessage, Object success) {
        Map<String, Object> map = new HashMap();
        map.put("isSuccess", 1);
        map.put("successMessage", successMessage);
        map.put("success", success);
        return map;
    }
}
