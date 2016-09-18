/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.mqclient;

import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.api.eventdata.NewsRecord;
import org.springframework.jms.annotation.JmsListener;

/**
 *
 * @author fatal1t
 */
public class NewsReciever {
    private final String balancesQueue = "forex.async.candles";
    
    @JmsListener(destination = balancesQueue, containerFactory = "myFactory" )
    public void receiveMessage(NewsRecord record) {
        System.out.println("Recieved new news " + record.getBody());
    }
}
