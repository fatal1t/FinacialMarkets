/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.config;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Filip
 */
@Entity(name = "QueueConfig")
@Table(schema = "configuration", name = "service_queues" )
public class QueueConfig implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private long id;
    @Column(name="service")
    private String service;
    
    @Column(name="consumer")
    private String consumer;
    
    @Column(name="targetqueue")
    private String targertqueue;
    
    @Column(name="replytoqueue")
    private String replytoqueue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getTargertqueue() {
        return targertqueue;
    }

    public void setTargertqueue(String targertqueue) {
        this.targertqueue = targertqueue;
    }

    public String getReplytoqueue() {
        return replytoqueue;
    }

    public void setReplytoqueue(String replytoqueue) {
        this.replytoqueue = replytoqueue;
    }
    
    public String toString()
    {
        return "[id: "+this.id +" service: " +this.service +" consumer: " +
                this.consumer + " targetQueue: " + this.targertqueue + " replyToQueue: "+this.replytoqueue;
    }
    
}
