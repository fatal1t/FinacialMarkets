/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.common;

/**
 *
 * @author Filip
 */
public class AsyncServiceObserver {
    private String queue;
    
    public AsyncServiceObserver(String queue)            
    {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }
    
    
}
