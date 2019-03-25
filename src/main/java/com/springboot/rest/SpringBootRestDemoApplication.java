package com.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
* The SpringBootRestDemoApplication File is used to start the application
* for the application.
*
* @author  ITMCS
* @version 1.0
* @since   2019-03-22 
*/
@Configuration
@ComponentScan({"com.springboot"})
@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootRestDemoApplication {
    
    SpringBootRestDemoApplication(){
        //Do nothing constructor
    }
    
    /**
    * This is the main method which starts the application .
    * @param args
    */    
    public static void main(String[] args) {
            SpringApplication.run(SpringBootRestDemoApplication.class, args);
    }

}
