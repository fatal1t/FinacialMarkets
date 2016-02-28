/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.logging.Level;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.fatal1t.forexapp.spring.services.getuserdata.GetUserDataService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public abstract class Endpoint {
    protected final Logger log = LogManager.getLogger(GetUserDataService.class.getName());
    public abstract void listen(TextMessage message);    
    public void respond(String message, String CorelId, Destination queue,JmsTemplate jmsTemplate )
    {
        
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);            
            nMessage.setJMSCorrelationID(CorelId);
            return nMessage;
        };
        
        jmsTemplate.send(queue, messageCreator);
        
        log.fatal("Odeslana zprava: "+ message);
    } 
    
    public void log()
    {
        
    }
}
