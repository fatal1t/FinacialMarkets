/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.mqclient;

/**
 *
 * @author fatal1t
 */
import org.fatal1t.backend.forexbackend.services.TickService;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TickReceiver {
    
    private final String ticksQueue ="forex.async.ticks";
    @Autowired
    private TickService tickService;
    @JmsListener(destination = ticksQueue, containerFactory = "myFactory" )
    public void receiveMessage(TickRecord record) {
        
        tickService.saveTick(record);
        System.out.println("Received <" + record.toString() + ">");
    }

}