/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.async;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.config.QueueConfig;
import org.fatal1t.forexapp.spring.services.common.AsyncService;
import org.fatal1t.forexapp.spring.services.common.AsyncServiceObserver;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service("DistributeCandlesAsync" )
public class DistributeCandlesAsync extends AsyncService {

    private final Logger log = Logger.getLogger(DistributeCandlesAsync.class.getName());
    @PostConstruct
    private void initService()
    {        
        this.queueConfigData.findByService("DistributeCandlesAsync").forEach((QueueConfig q) -> {
            this.observers.add(new AsyncServiceObserver(q.getTargertqueue()));
        });
    }
    
    @Override
    @JmsListener(destination = "forex.async.candles", containerFactory = "myJmsContainerFactory")
    public void listen(TextMessage message) throws JMSException{
        log.info("Prijata zprava: "+ message.getText());
        log(message, "DistributeCandlesAsync");
        MessageCreator msgCreator = (Session session) -> {
            TextMessage msg = session.createTextMessage();
            msg.setText(message.getText());
            msg.setJMSType(message.getJMSType());
            return msg;
        };
        this.observers.forEach((AsyncServiceObserver o ) -> {
            this.jmsTemplate.send(o.getQueue(), msgCreator);
        });
    }
    
    
    
    
}
