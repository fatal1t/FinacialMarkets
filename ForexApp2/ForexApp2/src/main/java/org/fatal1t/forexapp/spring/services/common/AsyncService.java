/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.fatal1t.forexapp.spring.config.QueueConfigInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 *
 * @author Filip
 */
public class AsyncService implements AsyncListener {

    @Autowired
    protected ConfigurableApplicationContext context;
    protected JmsTemplate jmsTemplate;
    protected QueueConfigInterface queueConfigData;
    protected List< AsyncServiceObserver> observers;
    private final String logQueue = "forex.infrastructure.log";
    @PostConstruct
    protected void init()
    {
        this.observers = new ArrayList<>();
        this.jmsTemplate = this.context.getBean(JmsTemplate.class); 
        this.queueConfigData =this.context.getBean(QueueConfigInterface.class);
    }
    @Override
    public void listen(TextMessage message) throws JMSException {
        
    }
    protected void distribute() {

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
