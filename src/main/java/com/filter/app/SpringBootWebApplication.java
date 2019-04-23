package com.filter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application launcher class.
 * 
 * @author Dipak Patil
 */
@SpringBootApplication(scanBasePackages = {"com.filter"})
public class SpringBootWebApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

}