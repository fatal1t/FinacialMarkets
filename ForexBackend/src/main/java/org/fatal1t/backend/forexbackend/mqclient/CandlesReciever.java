/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.mqclient;

import org.fatal1t.backend.forexbackend.services.CandleService;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class CandlesReciever {
    private final String balancesQueue = "forex.async.candles";
    
    @Autowired
    private CandleService service;
    
    @JmsListener(destination = balancesQueue, containerFactory = "myFactory" )
    public void receiveMessage(CandleDataRecord record) {
        
        System.out.println("Recieved candle wtih symbol +" + record.getSymbol() + " at time " + record.getCtm());
        this.service.saveCandle(record);
    }
}
