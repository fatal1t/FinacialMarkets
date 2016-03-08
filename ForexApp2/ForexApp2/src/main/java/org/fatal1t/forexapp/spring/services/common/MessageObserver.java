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
        while(this.message == null)
        {            
            Thread.sleep(1000);
            if(this.message != null)
                break;
            else
                continue;
        }
        
    }
    public TextMessage getMessage() {
        return message;
    }
    
    
}
