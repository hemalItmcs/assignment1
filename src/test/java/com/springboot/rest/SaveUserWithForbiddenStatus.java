/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboot.rest;

import com.springboot.rest.controller.UserController;
import com.springboot.rest.service.UserService;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
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
public class SaveUserWithForbiddenStatus {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserService userService;

    @Before
    public void before(){
        userService.deleteUserByUsername("hemal@itmusketeers.com");
    }
    
    @Test
    public void testSaveUserWithForbiddenStatus() throws Exception {
        String exampleUserJson = "{\"firstName\": \"Parth\",\"lastName\": \"Kansara\",\"userName\": \"hemaldodiya59@gmail.com\",\"password\": \"123456\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                            .post("/user/save")
                            .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
                            .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus(), anyOf(is(HttpStatus.CREATED.value())));

        requestBuilder = MockMvcRequestBuilders
                            .post("/user/save")
                            .accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
                            .contentType(MediaType.APPLICATION_JSON);
        result = mockMvc.perform(requestBuilder).andReturn();

        response = result.getResponse();
        assertThat(response.getStatus(), anyOf(is(HttpStatus.FORBIDDEN.value())));
    }
}
