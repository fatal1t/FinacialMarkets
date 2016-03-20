/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.calculations.listener;

import java.util.logging.Level;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service("CalculationListener")
public class CalculationListener {
   @Autowired
   ConfigurableApplicationContext context;
   private final Logger log = LogManager.getLogger(this.getClass().getName());
   
   @JmsListener(destination = "forex.calc.async.ticks", containerFactory = "myJmsContainerFactory")
   private void getTicks(TextMessage message)
   {
       try {
           log.info("Prijata zprava " + message.getText().substring(0, 50));
       } catch (JMSException ex) {
           log.fatal("Neco sa posralo v listeneru");
       }
   }
   @JmsListener(destination = "forex.calc.async.candles", containerFactory = "myJmsContainerFactory")
   private void getCandles(TextMessage message)
   {
       try {
           log.info("Prijata zprava " + message.getText().substring(0, 50));
       } catch (JMSException ex) {
           log.fatal("Neco sa posralo v listeneru");
       }
   }
   
    
    
}
