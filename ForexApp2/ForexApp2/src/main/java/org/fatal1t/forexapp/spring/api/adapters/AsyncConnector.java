/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.eventdata.BalanceRecord;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.NewsRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.fatal1t.forexapp.spring.api.eventdata.TradeRecord;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;



/**
 *
 * @author Filip
 */
@Component
public class AsyncConnector {
    @Autowired
    private ConfigurableApplicationContext context;
    private final Logger log = LogManager.getLogger(this.getClass().getName());
    private final String TickQueueName = "forex.async.ticks";
    private final String CandlesQueueName = "forex.async.candles";
    private final String BalanceQueueName = "forex.async.balance";
    private final String NewsQueueName = "forex.async.news";
    private final String TradeQueueName = "forex.async.trades";
    
    private void sendMessage(String message, String queue) throws JmsException, BeansException {
        MessageCreator messageCreator = (javax.jms.Session session1) -> session1.createTextMessage(message);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        log.info("Sending a new message: queue: " + queue + "message:" + message.substring(0, 50));
        jmsTemplate.send(queue, messageCreator);
    }
    
    public void sendMessage(CandleDataRecord record)
    {
        XStream xs = new XStream();
        sendMessage(xs.toXML(record), CandlesQueueName);
    }
    
    public void sendMessage(BalanceRecord record)
    {
        XStream xs = new XStream();
        sendMessage(xs.toXML(record), BalanceQueueName);
    }
    public void sendMessage(NewsRecord record)
    {
        XStream xs = new XStream();
        sendMessage(xs.toXML(record), NewsQueueName);
    }
    public void sendMessage(TickRecord record)
    {
        XStream xs = new XStream();
        sendMessage(xs.toXML(record), TickQueueName);
    }
    public void sendMessage(TradeRecord record)
    {
        XStream xs = new XStream();
        sendMessage(xs.toXML(record), TradeQueueName);
    }
    
}
