/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class AverigeRealtimeListener implements UpdateListener{


    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        System.out.println(Arrays.toString(newEvents[0].getEventType().getPropertyNames()));
        if(newEvents[0].getEventType().isProperty("averige")) 
        {
            Double averige =  (Double) newEvents[0].get("averige");
            System.out.println("New moving averige on 10 candles: " + averige);

        }  
    }
}
