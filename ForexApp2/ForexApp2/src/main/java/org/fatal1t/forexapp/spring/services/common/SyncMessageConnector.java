/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.UUID;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 *
 * @author Filip
 */

public class SyncMessageConnector {
    
    private static ConfigurableApplicationContext context;
    private static final SyncMessageConnector connector = new SyncMessageConnector();
    private static final Logger log = LogManager.getLogger(SyncMessageConnector.class.getName());
    private SyncMessageConnector()
    {
        
    }
 
    private static JmsTemplate jmsTemplate;
    @PostConstruct
    private void init() {        
        
     }
    public static SyncMessageConnector getConnector(ConfigurableApplicationContext context)
    {
        SyncMessageConnector.context = context;
        jmsTemplate = context.getBean(JmsTemplate.class);
        return connector;
    }
            

    public String request( final String request, String queue ) throws JMSException, InterruptedException {
        // Must pass true as the second param to start the connection
        Message response = jmsTemplate.execute(new SessionCallbackImpl(queue, request), true);
        if(response instanceof ActiveMQTextMessage)
        {
            ActiveMQTextMessage nresponse = (ActiveMQTextMessage) response;
            log.info("prijata zprava: " + nresponse.getText().substring(0, 100));
            return nresponse.getText();
        }
        else 
        {            
            log.fatal("Invalid objects in response");
            if(response != null)
                log.info(response.getClass().getName());
            return null;
        }
    }
    private class SessionCallbackImpl implements SessionCallback<Message> {

        private final String queue;
        private final String msg;

        public SessionCallbackImpl(String queue, String msg) {
            this.queue = queue;
            this.msg = msg;                  
            
        }

        @Override
        public Message doInJms(Session session) throws JMSException {
            DestinationResolver destinationResolver = jmsTemplate.getDestinationResolver();
            MessageConsumer consumer = null;
            MessageProducer producer = null;
            try {
                 Queue q = session.createQueue(queue+".response");
                final String correlationId = UUID.randomUUID().toString();
                final Destination requestQueue =
                        destinationResolver.resolveDestinationName( session, queue+".request", false );
                final Destination replyQueue =q;
                       //destinationResolver.resolveDestinationName( session, queue+".response", false );
                // Create the consumer first!
                consumer = session.createConsumer( replyQueue, "JMSCorrelationID = '" + correlationId + "'" );
                MessageObserver observer = new MessageObserver();
                MessageListener listener =  new MessageListenerImpl(observer);
                consumer.setMessageListener(listener);               
                final TextMessage textMessage = session.createTextMessage( msg );
                textMessage.setJMSCorrelationID( correlationId );
                textMessage.setJMSReplyTo( replyQueue );
                // Send the request second!
                log.info("Target queue: " + requestQueue.toString());
                log.info("Reply queue: " + replyQueue.toString());
                log.info("Sending message:" + msg);
                producer = session.createProducer( requestQueue );
                producer.send( requestQueue, textMessage );
                Thread.sleep(10000);
                //QueueBrowser browser = session.createBrowser(q);
                observer.waitMessage();        
                
                return observer.getMessage();
            }
            catch(JMSException | InterruptedException ex)
            {
                return null;
            } 
            
            finally {
                
                // Don't forget to close your resources
                JmsUtils.closeMessageConsumer( consumer );
                JmsUtils.closeMessageProducer( producer );
                
            }
        }
    }
}
