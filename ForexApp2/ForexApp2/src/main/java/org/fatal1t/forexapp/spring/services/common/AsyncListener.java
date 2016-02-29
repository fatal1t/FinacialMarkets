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
public interface AsyncListener {
    
    
    public void listen(TextMessage message);
    
    
    
}
