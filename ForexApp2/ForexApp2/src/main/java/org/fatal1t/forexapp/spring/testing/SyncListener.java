/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.testing;

import com.thoughtworks.xstream.XStream;
import fhl.main.adapters.sync.responses.GetUserDataResp;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.fatal1t.forexapp.session.AppSession;
import org.fatal1t.forexapp.spring.adapters.APISyncAdapter;
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
public class SyncListener {
    @Autowired
    private ConfigurableApplicationContext context;
    private APISyncAdapter APIAdapter; 

    
    @JmsListener(destination = "forex.sync.connector.request", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(TextMessage message) throws JMSException {
        System.out.println("Source Queue: "+message.getJMSDestination().toString());
        System.out.println("Target Queue:" + message.getJMSReplyTo().toString());
        System.out.println("Received <" + message.getText() + ">");
        if(this.APIAdapter == null)
        {
             this.APIAdapter = (APISyncAdapter) context.getBean(APISyncAdapter.class);
        }
        initiateAdapter();
        if(!initiateAdapter())
        {
            System.out.println("pruser s adapterem");
            sendMessage( "Error in adapter setting, cant login", message.getJMSCorrelationID(), message.getJMSReplyTo());
        }
        GetUserDataResp APIResponse = APIAdapter.GetUserData();
        XStream xs = new XStream();        
        
        sendMessage( xs.toXML(APIResponse), message.getJMSCorrelationID(), message.getJMSReplyTo());
    }
    
    
    
    private void sendMessage(String message, String CorelId, Destination queue) throws JmsException, BeansException {
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
            Message nMessage = session1.createTextMessage(message);            
            nMessage.setJMSCorrelationID(CorelId);
            return nMessage;
        };
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        System.out.println("Sending back" + message);
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
