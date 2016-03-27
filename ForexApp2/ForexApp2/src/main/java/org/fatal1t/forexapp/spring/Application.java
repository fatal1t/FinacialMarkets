/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.fatal1t.forexapp.spring.api.adapters.APIStreamingAdapter;
import org.fatal1t.forexapp.spring.calculations.engine.CandlesTempStorage;
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

    public static void main(String[] args) throws JMSException, InterruptedException {        
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("activemq-data"));

        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        log.info("Session is loaded");
        /*        CandlesRepository repository = context.getBean(CandlesRepository.class);
        List<String> symbols = new ArrayList<>();
        symbols.add("EURSUD");
        symbols.add("USDCZK");
        symbols.add("EURCHF");
        List<Candle> list = repository.findBySymbolInOrderByTime(symbols);
        System.out.println(list.get(0).toString());
        System.out.println(list.size());*/
        System.out.println("Tady se neco deje");
        CandlesTempStorage storage = context.getBean(CandlesTempStorage.class);

        

        //respond(xs.toXML(request), UUID.randomUUID().toString(), "forex.sync.getuserdata.request", "forex.sync.getuserdata.response", jmsTemplate );
        
        //SyncMessageConnector sync = SyncMessageConnector.getConnector(context);
        //sync.request(xs.toXML(request), "forex.sync.listener.connector");
 
        // Send a message
        //APIStreamingAdapter adapter = context.getBean(APIStreamingAdapter.class);
        //adapter.start();
        //ClientSessionManager manager = context.getBean(ClientSessionManager.class);
        //manager.init();
        
        
        //APIStreamingAdapter ad = context.getBean(APIStreamingAdapter.class);
        //ad.start();
        // TODO code application logic here
    }
    }

