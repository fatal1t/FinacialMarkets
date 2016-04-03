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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.forexapp.spring.resources.db.CandlesRepository;
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
@Service("GetCandlesHistory")
public class GetCandlesHistory extends Endpoint {
    @Autowired
    private final ConfigurableApplicationContext context;
    private final Logger log = LogManager.getLogger(this.getClass().getName());
    private TextMessage requestMessage;
    private SyncMessageConnector connector;
    @Autowired
    public GetCandlesHistory(ConfigurableApplicationContext context, JmsTemplate template )
    {
        super();
        this.context = context;
    }
    @PostConstruct
    private void init()
    {
        this.jmsTemplate = this.context.getBean(JmsTemplate.class);
    }
    
    @JmsListener(destination = "forex.sync.getcandleshistory.request", containerFactory = "myJmsContainerFactory")
    @Override
    public void listenRequest(TextMessage message) {
        try {
            System.out.println("Neco se deje");
            log.info("Source Queue: "+ message.getJMSDestination().toString());
            log.info( "Received "+ message.getText()+ " " + message.getJMSType());
            log(message, "GetCandlesHistory");
            this.requestMessage = message;
            
            respond(message.getText(), message.getJMSCorrelationID(), "GetCandlesHistory", "forex.sync.listener.connector.request", "forex.sync.connector.getcandleshistory.response");
            
        } catch (JMSException ex) {
            log.fatal("Neco se posralo po cesto do BE" + Arrays.toString(ex.getStackTrace()));
        }
    }
    @JmsListener(destination = "forex.sync.connector.getcandleshistory.response", containerFactory = "myJmsContainerFactory" )
    public void listenResponse(TextMessage message) throws JMSException
    {        
        respond(message.getText(), this.requestMessage.getJMSCorrelationID(), 
                message.getJMSType(), this.requestMessage.getJMSReplyTo());
        message.setJMSReplyTo(this.requestMessage.getJMSReplyTo());
         log(message, "GetCandlesHistory");
        //log.info(message.getText().substring(0, 100));
    }
    
}
