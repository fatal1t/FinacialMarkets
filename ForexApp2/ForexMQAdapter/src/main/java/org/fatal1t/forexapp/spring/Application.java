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
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.WebApplicationInitializer;
/**
 *
 * @author Filip
 */
    
    
@SpringBootApplication

public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}


/*public class Application {
* @param args the command line arguments

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
}*/

