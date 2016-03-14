/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring;

import java.io.File;
import java.sql.Timestamp;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.fatal1t.forexapp.spring.api.adapters.APIStreamingAdapter;
import org.fatal1t.forexapp.spring.config.QueueConfig;
import org.fatal1t.forexapp.spring.config.QueueConfigInterface;
import org.fatal1t.forexapp.spring.resources.db.Candle;
import org.fatal1t.forexapp.spring.resources.db.CandlesRepository;
import org.fatal1t.forexapp.spring.resources.db.UserData;
import org.fatal1t.forexapp.spring.resources.db.UserDataRepository;
import org.fatal1t.forexapp.spring.services.dblog.LogRecord;
import org.fatal1t.forexapp.spring.services.dblog.LogRecordRepository;
import org.fatal1t.forexapp.spring.session.ClientSessionManager;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.util.FileSystemUtils;
/**
 *
 * @author Filip
 */
    
    @EnableJpaRepositories
    @SpringBootApplication
    public class Application {
        @Bean
        JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
        };
        
 
        
    /**
     * @param args the command line arguments
     */
        static final Logger log = LogManager.getLogger(Application.class);
        /*    static public void respond(String message, String CorelId, String queue, String replyTo, JmsTemplate jmsTemplate )
        {
        
        MessageCreator messageCreator = (javax.jms.Session session1) -> {
        Message nMessage = session1.createTextMessage(message);
        nMessage.setJMSCorrelationID(CorelId);
        
        nMessage.setJMSReplyTo(session1.createQueue(replyTo));
        return nMessage;
        };
        
        jmsTemplate.send(queue, messageCreator);
        
        log.fatal("Odeslana zprava: "+ message);
        } */

    public static void main(String[] args) throws JMSException, InterruptedException {        
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        log.info("Session is loaded");
        UserDataRepository repository = context.getBean(UserDataRepository.class);
        
        

        //respond(xs.toXML(request), UUID.randomUUID().toString(), "forex.sync.getuserdata.request", "forex.sync.getuserdata.response", jmsTemplate );
        
        //SyncMessageConnector sync = SyncMessageConnector.getConnector(context);
        //sync.request(xs.toXML(request), "forex.sync.listener.connector");
 
        // Send a message
        //APIStreamingAdapter adapter = context.getBean(APIStreamingAdapter.class);
        //adapter.start();
        CandlesRepository repo = context.getBean(CandlesRepository.class);
        Candle c = new Candle();
        c.setClose(1.0);
        c.setHigh(1.3);
        c.setLow(0.8);
        c.setOpen(1.1);
        c.setQuoteId(1);
        c.setSymbol("EURSUD");
        c.setVol(2.0);
        c.setTime(Timestamp.valueOf("2015-03-14 22:40:00"));
        repo.save(c);
        ClientSessionManager manager = context.getBean(ClientSessionManager.class);
        manager.init();
        
        APIStreamingAdapter ad = context.getBean(APIStreamingAdapter.class);
        ad.start();
        // TODO code application logic here
    }
    /*
    @Bean
    public CommandLineRunner demo(UserDataRepository repository) {
    return (args) -> {
    // save a couple of customers
    repository.save(new UserData("Bauer"));
    repository.save(new UserData("O'Brian"));
    repository.save(new UserData("Bauer"));
    repository.save(new UserData("Palmer"));
    repository.save(new UserData("Dessler"));
    
    // fetch all customers
    log.info("Customers found with findAll():");
    log.info("-------------------------------");
    for (UserData customer : repository.findAll()) {
    log.info(customer.toString());
    }
    log.info("");
    
    // fetch an individual customer by ID
    UserData customer = repository.findOne(1L);
    log.info("Customer found with findOne(1L):");
    log.info("--------------------------------");
    log.info(customer.toString());
    log.info("");
    
    // fetch customers by last name
    log.info("Customer found with findByLastName('Bauer'):");
    log.info("--------------------------------------------");
    repository.findByusername("Bauer").stream().forEach((bauer) -> {
    log.info(bauer.toString());
    });
    log.info("");
    };
    }*/
    
}
