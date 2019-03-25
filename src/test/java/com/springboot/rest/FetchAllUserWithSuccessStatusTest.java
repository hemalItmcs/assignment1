/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest;

import com.springboot.rest.controller.UserController;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author ITMCS-1
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class FetchAllUserWithSuccessStatusTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testFetchAllUserWithSuccessStatus() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                            .get("/user/fetchAll");
                            
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus(), anyOf(is(HttpStatus.OK.value())));
        //assertThat(response.getStatus(), anyOf(is(HttpStatus.CONFLICT.value()), is(HttpStatus.CREATED.value()),is(HttpStatus.FORBIDDEN.value()),is(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }
        
}
