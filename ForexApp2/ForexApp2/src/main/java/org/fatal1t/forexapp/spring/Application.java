/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring;

import com.thoughtworks.xstream.XStream;
import fhl.main.adapters.sync.requests.GetUserDataReq;
import org.fatal1t.forexapp.spring.testing.SyncMessageConnector;
import org.fatal1t.forexapp.spring.adapters.APIStreamingAdapter;
import org.fatal1t.forexapp.spring.adapters.APISyncAdapter;
import java.io.File;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.session.SessionLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.FileSystemUtils;
/**
 *
 * @author Filip
 */
    @SpringBootApplication  
    @EnableJms
public class Application {
        @Bean
        JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
        }
        
    /**
     * @param args the command line arguments
     */
        static final Logger log = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws JMSException {        
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        //APISyncAdapter syncAdapter = APISyncAdapter.GetAdapter("DEMO");
        //SessionLoader loader = new SessionLoader(syncAdapter);
        log.info("Session is loaded");
        
        GetUserDataReq request = new GetUserDataReq();
        
        SyncMessageConnector sync = new SyncMessageConnector(context);
        XStream xs = new XStream();
        sync.request(xs.toXML(request), "forex.sync.getuserdata");

        // Send a message
        //APIStreamingAdapter adapter = new APIStreamingAdapter();
        //adapter.start(org.fatal1t.forexapp.session.AppSession.getSession());
        // TODO code application logic here
    }
    
}
