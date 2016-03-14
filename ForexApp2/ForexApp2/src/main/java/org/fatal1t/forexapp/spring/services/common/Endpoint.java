/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;


import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.services.sync.GetUserDataService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public abstract class Endpoint {
    protected JmsTemplate jmsTemplate;
    protected final Logger log = LogManager.getLogger(GetUserDataService.class.getName());
    private final String logQueue = "forex.infrastructure.log";
    public abstract void listenRequest(TextMessage message);    
    

    /**
     *
     * @param message
     * @param CorelId
     * @param MessageType
     * @param queue
     * @param jmsTemplate
     */
    protected void respond(String message, String CorelId, String MessageType, Destination queue)
    {
        
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);
            nMessage.setJMSType(MessageType);
            nMessage.setJMSCorrelationID(CorelId);
            return nMessage;
        };
        
        jmsTemplate.send(queue, messageCreator);
        log.info("Target queue: " + queue);        
        log.info("Odeslana zprava: "+ message.substring(0, 50));
    } 
    protected void respond(String message, String CorelId, String MessageType, String queue, String replyTo)
    {
        
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);      
            nMessage.setJMSType(MessageType);
            nMessage.setJMSCorrelationID(CorelId);
            nMessage.setJMSReplyTo(jmsTemplate.getDestinationResolver().resolveDestinationName(session1, replyTo, false));
            return nMessage;
        };
        
        jmsTemplate.send(queue, messageCreator);
        log.info("Target queue: " + queue);
        log.info("Odeslana zprava: "+ message.substring(0,50));
    } 
    public void log(TextMessage message, String service) {
        MessageCreator messageCreator = (Session session1) -> {
            Message nMessage = session1.createTextMessage(message.getText());
            nMessage.setJMSType(message.getJMSType());
            nMessage.setJMSCorrelationID(message.getJMSCorrelationID());
            nMessage.setJMSReplyTo(message.getJMSReplyTo());
            nMessage.setStringProperty("service", service);
            nMessage.setStringProperty("sourceQueue", message.getJMSDestination().toString());
            return nMessage;
        };
        this.jmsTemplate.send(logQueue, messageCreator);
    }
    
}
