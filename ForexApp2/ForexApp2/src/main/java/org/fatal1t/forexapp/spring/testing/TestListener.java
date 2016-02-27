/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.testing;

import com.thoughtworks.xstream.XStream;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public class TestListener {
    @Autowired
    ConfigurableApplicationContext context;
    
    @JmsListener(destination = "test.filip.request", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(TextMessage message) throws JMSException {
        XStream xs = new XStream();
        xs.fromXML(message.getText());
        
        System.out.println("Received <" + message.getText() + ">");
        sendMessage("prijato z test.filip.request", message.getJMSCorrelationID(), message.getJMSReplyTo());
    }
    
    private void sendMessage(String message, String CorelId, Destination queue) throws JmsException, BeansException {
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);            
            nMessage.setJMSCorrelationID(CorelId);
            return nMessage;
        };        
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending a new message.");        
        jmsTemplate.send(queue, messageCreator);
    }
    
    
}
