/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.testing;

import java.util.UUID;
import org.apache.log4j.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.LogManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public class SyncMessageConnector {
    
    @Autowired
    private ConfigurableApplicationContext context;
    private static final Logger log = LogManager.getLogger(SyncMessageConnector.class.getName());
    private static final class ProducerConsumer implements SessionCallback<Message> {
 
        private static final int TIMEOUT = 500000;
 
        private final String msg;
 
        private final DestinationResolver destinationResolver;
 
        private final String queue;
 
        public ProducerConsumer( final String msg, String queue, final DestinationResolver destinationResolver ) {
            this.msg = msg;
            this.queue = queue;
            this.destinationResolver = destinationResolver;
        }
        @Override 
        public Message doInJms( final Session session ) throws JMSException {
            
            MessageConsumer consumer = null;
            MessageProducer producer = null;
            try {
                final String correlationId = UUID.randomUUID().toString();
                final Destination requestQueue =
                        destinationResolver.resolveDestinationName( session, queue+".request", false );
                final Destination replyQueue =
                        destinationResolver.resolveDestinationName( session, queue+".response", false );
                // Create the consumer first!
                consumer = session.createConsumer( replyQueue, "JMSCorrelationID = '" + correlationId + "'" );
                log.info("Target queue: " + requestQueue.toString());
                log.info("Reply queue: " + replyQueue.toString());
                final TextMessage textMessage = session.createTextMessage( msg );
                textMessage.setJMSCorrelationID( correlationId );
                textMessage.setJMSReplyTo( replyQueue );
                // Send the request second!
                log.info("Sending message:" + msg);
                producer = session.createProducer( requestQueue );
                producer.send( requestQueue, textMessage );
                // Block on receiving the response with a timeout                
                return consumer.receive(TIMEOUT);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return session.createTextMessage("vyskytla se chyba");
            }
                    
            finally {
                // Don't forget to close your resources
                JmsUtils.closeMessageConsumer( consumer );
                JmsUtils.closeMessageProducer( producer );
            }
        }

        

    }
 
    private final JmsTemplate jmsTemplate;
    @Autowired
    public SyncMessageConnector(ConfigurableApplicationContext context) {
        this.context = context;
        this.jmsTemplate = this.context.getBean(JmsTemplate.class);
     }

            

    public String request( final String request, String queue ) throws JMSException {
        // Must pass true as the second param to start the connection
        
        ActiveMQMessage response = (ActiveMQMessage)  jmsTemplate.execute((SessionCallback<?>) new ProducerConsumer( request, queue, jmsTemplate.getDestinationResolver() ), true);
        if(response instanceof ActiveMQTextMessage)
        {
            ActiveMQTextMessage nresponse = (ActiveMQTextMessage) response;
            log.info("prijata zprava: " + nresponse.getText());
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
}
