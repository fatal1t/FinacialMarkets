/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import java.util.List;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author Filip
 */
public class AsyncService implements AsyncListener{

    @Autowired
    protected ConfigurableApplicationContext context;
    protected List< AsyncServiceObserver> observers;
    @Override
    public void listen(TextMessage message) throws JMSException {
        
    }

    
    protected void log(TextMessage message) {
        
    }

    protected void distribute() {

    }
    
}
