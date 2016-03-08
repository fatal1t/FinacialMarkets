/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.destination.DestinationResolver;

/**
 *
 * @author Filip
 */
public class EndpointConnector {
    @Autowired
    private final ConfigurableApplicationContext context;
    private static final Logger log = LogManager.getLogger(EndpointConnector.class.getName());
    private static final class ProducerConsumer implements SessionCallback<Message> {
 
        private static final int TIMEOUT = 500000;
 
        private final String msg;
        private final String messageType;
 
        private final DestinationResolver destinationResolver;
 
        private final String targetQueue;
        private final String replyTo;
        
 
        public ProducerConsumer( final String msg, String messageType, String targetQeue, String replyTo,  final DestinationResolver destinationResolver ) {
            this.msg = msg;
            this.targetQueue = targetQeue;
            this.replyTo = replyTo;
            this.destinationResolver = destinationResolver;
            this.messageType = messageType;
        }
        @Override 
        public Message doInJms( final Session session ) throws JMSException {
            
            MessageConsumer consumer = null;
            MessageProducer producer = null;
            try {
                final String correlationId = UUID.randomUUID().toString();
                final Destination requestQueue =
                        destinationResolver.resolveDestinationName( session, targetQueue, false );
                final Destination replyQueue =
                        destinationResolver.resolveDestinationName( session, replyTo, false );
                // Create the consumer first!
                consumer = session.createConsumer( replyQueue, "JMSCorrelationID = '" + correlationId + "'" );
                MessageObserver observer = new MessageObserver();
                MessageListener listener =  new MessageListenerImpl(observer);
                consumer.setMessageListener(listener);
                log.info("Target queue: " + requestQueue.toString());
                log.info("Reply queue: " + replyQueue.toString());
                final TextMessage textMessage = session.createTextMessage( msg );
                textMessage.setJMSCorrelationID( correlationId );
                textMessage.setJMSReplyTo( replyQueue );
                textMessage.setJMSType(this.messageType);
                textMessage.setJMSMessageID(UUID.randomUUID().toString());
                // Send the request second!
                log.info("Sending message:" + msg.substring(0, 50));
                producer = session.createProducer( requestQueue );
                producer.send( requestQueue, textMessage );
                // Block on receiving the response with a timeout   
                long startTime  = System.nanoTime();
                while(observer.getMessage() == null)
                {
                    long seconds = TimeUnit.SECONDS.convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
                    observer.waitMessage();
                    if(seconds < 30 )
                    {
                        if(observer.getMessage() != null)
                        {
                            break;                            
                        }
                    }
                    else
                    {
                        return null;
                    }
                }

                return observer.getMessage();
            }
            catch(JMSException | InterruptedException ex)
            {
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
    public EndpointConnector(ConfigurableApplicationContext context) {
        this.context = context;
        this.jmsTemplate = this.context.getBean(JmsTemplate.class);
     }
    

    protected String request( final String request, String messageType, String targetQueue, String replyTo) throws JMSException {
        // Must pass true as the second param to start the connection
        
        ActiveMQMessage response = (ActiveMQMessage)jmsTemplate.execute((SessionCallback<?>) 
                new ProducerConsumer( request, messageType, targetQueue, replyTo, jmsTemplate.getDestinationResolver() ), true);
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
    
}
