/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Filip
 */
public class MessageListenerImpl implements MessageListener{
    private static Logger log = LogManager.getLogger("MessageListener");

    private MessageObserver observer;
    
    public MessageListenerImpl(MessageObserver observer)
    {
        this.observer = observer;
    }
    @Override
    public void onMessage(Message message) {
        try {
            log.info("prijata zprava" + message.getJMSCorrelationID());
            if(message instanceof TextMessage)
            {
                log.info("zapisuju do Listeneru a notifikuju observera " + message.getJMSCorrelationID());
                this.observer.notify((TextMessage) message);
            }
            else
            {
                TextMessage eMessage = new ActiveMQTextMessage();
                try {
                    log.fatal("nespravny format message" + message.getJMSCorrelationID());                ;
                    eMessage.setText("Chyba pri prijmani zpravy");
                } catch (JMSException ex) {
                    java.util.logging.Logger.getLogger(MessageListenerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.observer.notify(eMessage);
            }
        } catch (JMSException ex) {
            log.fatal("nekde na ceste je chyba ");
        }
        
        
    }
    
    
}