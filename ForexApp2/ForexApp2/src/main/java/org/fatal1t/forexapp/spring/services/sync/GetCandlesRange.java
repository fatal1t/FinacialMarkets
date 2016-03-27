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

/**
 *
 * @author Filip
 */
public class GetCandlesRange extends Endpoint {
   
    private final ConfigurableApplicationContext context;
    private TextMessage requestMessage;
    private SyncMessageConnector connector;
    @Autowired
    public GetCandlesRange(ConfigurableApplicationContext context, JmsTemplate template )
    {
        super();
        this.context = context;
        this.jmsTemplate = this.context.getBean(JmsTemplate.class);
    }
    @JmsListener(destination = "forex.sync.getcandlesrange.request", containerFactory = "myJmsContainerFactory")
    @Override
    public void listenRequest(TextMessage message) {
        try {
            log.info("Source Queue: "+ message.getJMSDestination().toString());
            log.info( "Received "+ message.getText()+ " " + message.getJMSType());
            log(message, "GetCandlesRange");
            this.requestMessage = message;
            
            respond(message.getText(), message.getJMSCorrelationID(), "GetCandlesRange", "forex.sync.listener.connector.request", "forex.sync.connector.getcandlesrange.response");
            
        } catch (JMSException ex) {
            log.fatal("Neco se posralo po cesto do BE" + Arrays.toString(ex.getStackTrace()));
        }
    }
    @JmsListener(destination = "forex.sync.connector.getcandlesrange.response", containerFactory = "myJmsContainerFactory" )
    public void listenResponse(TextMessage message) throws JMSException
    {        
        respond(message.getText(), this.requestMessage.getJMSCorrelationID(), 
                message.getJMSType(), this.requestMessage.getJMSReplyTo());
        message.setJMSReplyTo(this.requestMessage.getJMSReplyTo());
         log(message, "GetCandlesRange");
        //log.info(message.getText().substring(0, 100));
    }
    
}
