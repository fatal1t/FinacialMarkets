/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.testing;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Filip
 */
@Component
public class TestListener {
    @Autowired
    private ConfigurableApplicationContext context;
    private final Logger log = LogManager.getLogger(TestListener.class.getName());

    
    @JmsListener(destination = "forex.async.tick.candlestorage",  containerFactory = "myJmsContainerFactory")
    public void receiveMessage(TextMessage message) throws JMSException {
        //log.info("Source Queue: "+message.getJMSDestination().toString());
        
        //log.info("Received:"+ message.getJMSCorrelationID()+ " <" + message.getText().substring(0,100) + ">");
       /*
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
                sendMessage(xs.toXML(APIResp), message.getJMSCorrelationID(), message.getJMSReplyTo());
                break;
            }
        }*/
    }
}
