/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.async;

import org.fatal1t.forexapp.spring.services.common.AsyncListener;
import javax.jms.TextMessage;
import org.fatal1t.forexapp.spring.services.common.AsyncService;
import org.springframework.jms.annotation.JmsListener;

/**
 *
 * @author Filip
 */
public class DistributeCandlesAsync extends AsyncService {

    
    @Override
    @JmsListener(destination = "forex.async.candles")
    public void listen(TextMessage message) {
        

        
    }
    
    
    
    
}
