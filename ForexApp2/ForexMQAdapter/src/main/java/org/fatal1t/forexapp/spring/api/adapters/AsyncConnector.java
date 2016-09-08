/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import javax.jms.DeliveryMode;
import javax.jms.Topic;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.eventdata.BalanceRecord;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.NewsRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TradeRecord;
import org.fatal1t.forexapp.spring.config.QueueConfigInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;



/**
 *
 * @author Filip
 */
@Component
public class AsyncConnector {
    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private QueueConfigInterface queues;
    private final Logger log = LogManager.getLogger(this.getClass().getName());
    private final String TickQueueName = "forex.async.ticks";
    private final String CandlesQueueName = "forex.async.candles";
    private final String BalanceQueueName = "forex.async.balance";
    private final String NewsQueueName = "forex.async.news";
    private final String TradeQueueName = "forex.async.trades";
    
    private void sendMessage(Object message, String queue) throws JmsException, BeansException {
        //MessageCreator messageCreator = (javax.jms.Session session1) -> session1.createTextMessage(message);
        log.info("Sending a new message: queue: " + queue + "message:" + message.toString().substring(0, 50));
        //jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setReceiveTimeout(60000);        
        jmsTemplate.convertAndSend(queue, message);      
    }
    
    public void sendMessage(CandleDataRecord record)
    {        
        sendMessage(record, CandlesQueueName);
    }
    
    public void sendMessage(BalanceRecord record)
    {
        sendMessage(record,  BalanceQueueName);
    }
    public void sendMessage(NewsRecord record)
    {
        sendMessage(record, NewsQueueName);
    }
    public void sendMessage(TickRecord record)
    {
        sendMessage(record,  TickQueueName);
    }
    public void sendMessage(TradeRecord record)
    {
        sendMessage(record, TradeQueueName);
    }
    
}
