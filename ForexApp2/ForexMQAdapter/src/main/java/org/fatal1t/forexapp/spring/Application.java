/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring;

import java.io.File;
import javax.jms.JMSException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.fatal1t.forexapp.spring.api.adapters.APIStreamingAdapter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;
/**
 *
 * @author Filip
 */
    
    
    @SpringBootApplication
    public class Application {

        
 
        
    /**
     * @param args the command line arguments
     */
        static final Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws JMSException, InterruptedException {        
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        log.info("Session is loaded");
       
        APIStreamingAdapter ad = context.getBean(APIStreamingAdapter.class);
        ad.start();
        // TODO code application logic here
    }

    
}

