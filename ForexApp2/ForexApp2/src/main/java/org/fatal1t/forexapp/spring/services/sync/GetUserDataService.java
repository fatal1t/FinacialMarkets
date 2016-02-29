/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.sync;

import java.util.Arrays;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.fatal1t.forexapp.spring.services.common.Endpoint;
import org.fatal1t.forexapp.spring.services.common.SyncMessageConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service("GetUserDataService")
public class GetUserDataService extends Endpoint{
    @Autowired
    private ConfigurableApplicationContext context;
    private SyncMessageConnector connector;

    public GetUserDataService()
    {
                   
    }
    @PostConstruct
    private void init()
    {
        this.connector = new SyncMessageConnector(context);
    }

    @JmsListener(destination = "forex.sync.getuserdata.request", containerFactory = "myJmsContainerFactory")
    @Override
    public void listen(TextMessage message) {
        try {
            log.info("Source Queue: "+ message.getJMSDestination().toString());
            log.info( "Received "+message.getText());
            log();
            String response = connector.request(message.getText(), "forex.sync.connector");
            respond(response, message.getJMSCorrelationID(), message.getJMSReplyTo(), this.context.getBean(JmsTemplate.class));
            
        } catch (JMSException ex) {
            log.fatal("Neco se posralo po cesto do BE" + Arrays.toString(ex.getStackTrace()));
        }
        
    }
        
}
