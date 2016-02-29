/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

import javax.jms.TextMessage;

/**
 *
 * @author Filip
 */
class MessageObserver {
    
    private TextMessage message;

    MessageObserver() {
        this.message = null;
    }
    public void notify(TextMessage message)
    {
        this.message = message;
        
    }
    public void waitMessage() throws InterruptedException
    {
        Thread.sleep(10);
    }
    public TextMessage getMessage() {
        return message;
    }
    
    
}
