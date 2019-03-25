package com.springboot.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
* The AppConfig File is configure the database connection
* for the application.
*
* @author  ITMCS
* @version 1.0
* @since   2019-03-22 
*/
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
		
    @Autowired
    private Environment env;
	
    /**
    * This is the dataSource method which initialize the data source for the application .
    * @return DriverManagerDataSource.
    */
    @Bean
    public DriverManagerDataSource dataSource(){
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }	
    
    /**
    * This is the jdbcTemplate method which initialize the jdbcTemplate for the application for querying the database.
    * @param dataSource This method takes the datasource as a parameter
    * @return JdbcTemplate.
    */
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    
    
    /**
    * This is the transactionManager method which initialize the transactionManager for the application for manage the transactions.
    * @param dataSource This method takes the datasource as a parameter
    * @return PlatformTransactionManager.
    */
    @Bean
    public PlatformTransactionManager transactionManager(DriverManagerDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
