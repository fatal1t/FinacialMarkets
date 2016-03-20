/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.sync;

import java.util.Arrays;
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
    private TextMessage requestMessage;
    private SyncMessageConnector connector;
    public GetUserDataService()
    {
                   
    }
    @PostConstruct
    private void init()
    {
        this.jmsTemplate = this.context.getBean(JmsTemplate.class);
    }

    /**
     *
     * @param message
     */
    @JmsListener(destination = "forex.sync.getuserdata.request", containerFactory = "myJmsContainerFactory")
    @Override
    public void listenRequest(TextMessage message) {
        try {
            log.info("Source Queue: "+ message.getJMSDestination().toString());
            log.info( "Received "+ message.getText()+ " " + message.getJMSType());
            log(message, "GetUserDataService");
            this.requestMessage = message;
            respond(message.getText(), message.getJMSCorrelationID(), "GetUserData", "forex.sync.listener.connector.request", "forex.sync.connector.getuserdata.response");
            //String response = connector.request(message.getText(), "forex.sync.listener.connector");
            //respond(response, message.getJMSCorrelationID(), message.getJMSType(), message.getJMSReplyTo(), this.context.getBean(JmsTemplate.class));
            
        } catch (JMSException ex) {
            log.fatal("Neco se posralo po cesto do BE" + Arrays.toString(ex.getStackTrace()));
        }
        
    }
    @JmsListener(destination = "forex.sync.connector.getuserdata.response", containerFactory = "myJmsContainerFactory" )
    public void listenResponse(TextMessage message) throws JMSException
    {        
        respond(message.getText(), this.requestMessage.getJMSCorrelationID(), 
                message.getJMSType(), this.requestMessage.getJMSReplyTo());
        message.setJMSReplyTo(this.requestMessage.getJMSReplyTo());
        log(message,"GetUserDataService");
        //log.info(message.getText().substring(0, 100));
    }
    
        
}
