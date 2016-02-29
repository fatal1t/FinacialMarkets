/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring;

import com.thoughtworks.xstream.XStream;
import org.fatal1t.forexapp.spring.api.requests.GetUserDataReq;
import org.fatal1t.forexapp.spring.services.common.SyncMessageConnector;
import org.fatal1t.forexapp.spring.api.adapters.APIStreamingAdapter;
import org.fatal1t.forexapp.spring.api.adapters.APISyncAdapter;
import java.io.File;
import java.util.UUID;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
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
import org.springframework.jms.core.MessageCreator;
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
    static public void respond(String message, String CorelId, String queue,JmsTemplate jmsTemplate )
    {
        
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);            
            nMessage.setJMSCorrelationID(CorelId);
            return nMessage;
        };
        
        jmsTemplate.send(queue, messageCreator);
        
        log.fatal("Odeslana zprava: "+ message);
    } 

    public static void main(String[] args) throws JMSException {        
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        //APISyncAdapter syncAdapter = APISyncAdapter.GetAdapter("DEMO");
        //SessionLoader loader = new SessionLoader(syncAdapter);
        log.info("Session is loaded");
        
        GetUserDataReq request = new GetUserDataReq();
        XStream xs = new XStream();
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        //respond(xs.toXML(request), UUID.randomUUID().toString(), "forex.sync.getuserdata.request", jmsTemplate );
        
        SyncMessageConnector sync = new SyncMessageConnector(context);
        sync.request(xs.toXML(request), "forex.sync.connector");
 
        // Send a message
        //APIStreamingAdapter adapter = new APIStreamingAdapter();
        //adapter.start(org.fatal1t.forexapp.session.AppSession.getSession());
        // TODO code application logic here
    }
    
}
