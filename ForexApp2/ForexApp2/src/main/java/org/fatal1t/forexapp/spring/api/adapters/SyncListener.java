/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters;

import com.thoughtworks.xstream.XStream;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetTradingHoursReq;
import org.fatal1t.forexapp.spring.session.AppSession;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetAllSymbolsResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetTradingHoursResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetUserDataResp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
@EnableJms
public class SyncListener {
    @Autowired
    private ConfigurableApplicationContext context;
    private final Logger log = LogManager.getLogger(SyncListener.class.getName());
    private APISyncAdapter APIAdapter; 

    
    @JmsListener(destination = "forex.sync.listener.connector.request", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(TextMessage message) throws JMSException {
        log.info("Source Queue: "+message.getJMSDestination().toString());
        log.info("Target Queue: " + message.getJMSReplyTo().toString());
        log.info("Received:"+ message.getJMSCorrelationID()+" " +message.getJMSType() +" <" + message.getText().substring(0, 50) + ">");
        if(this.APIAdapter == null)
        {
             this.APIAdapter = (APISyncAdapter) context.getBean(APISyncAdapter.class);
        }
        initiateAdapter();
        if(!initiateAdapter())
        {
            log.info("pruser s adapterem");
            sendMessage( "Error in adapter setting, cant login", message.getJMSCorrelationID(), message.getJMSReplyTo());
        }        
        XStream xs = new XStream();    
        Object o = xs.fromXML(message.getText());       
        switch(message.getJMSType())
        {
            case "GetUserData" : {
                GetUserDataResp APIResponse = this.APIAdapter.GetUserData();
                sendMessage( xs.toXML(APIResponse), message.getJMSCorrelationID(), message.getJMSReplyTo());
                break;
            }
            case "GetAllSymbols" : {
                GetAllSymbolsResp APIResp = this.APIAdapter.GetAllSymbols();
                sendMessage(xs.toXML(APIResp), message.getJMSCorrelationID(), message.getJMSReplyTo());
                break;
            }
            case "GetTradingHours" :
            {
                GetTradingHoursResp APIResp = this.APIAdapter.GetTradingHours((GetTradingHoursReq) o);
                System.out.println(xs.toXML(APIResp));
                sendMessage(xs.toXML(APIResp), message.getJMSCorrelationID(), message.getJMSReplyTo());
                break;
            }
        }
    }
    
    
    
    private void sendMessage(String message, String CorelId, Destination queue) throws JmsException, BeansException {
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);            
            nMessage.setJMSCorrelationID(CorelId);            
            
            return nMessage;
        };       
        
        JmsTemplate jmsTemplate = this.context.getBean(JmsTemplate.class);
        log.info("Target queue: " + queue.toString());
        log.info("Sending back: " +CorelId+ " "+ message.substring(0, 10));
        jmsTemplate.send(queue, messageCreator);
    } 
    
    private boolean initiateAdapter()
    {
        if(this.APIAdapter.isConnected)
            return true;
        else
        {
            this.APIAdapter.init(AppSession.getSession().getConfiguration().getServerType().name(), 
                    AppSession.getSession().getConfiguration().getUsername(),
                    AppSession.getSession().getConfiguration().getPassword());
            return this.APIAdapter.isConnected;
        }

    }
    
}
