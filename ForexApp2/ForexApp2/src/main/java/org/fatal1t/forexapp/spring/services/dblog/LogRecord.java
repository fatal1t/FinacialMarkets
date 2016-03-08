/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.dblog;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 *
 * @author Filip
 */
@Entity
@Table(schema = "log", name = "application_log")
public class LogRecord {
    /*
    sid character varying(40) NOT NULL,
    logtime timestamp without time zone,
    currentcomponent character varying(40),
    sourcequeue character varying(40),
    targetqueue character varying(40),
    correlid character varying(40),
    request text,
        */
    @Id
    private String SID;
    @Column(name="logtime", insertable = false)
    private Timestamp time;
    @Column(name = "currentcomponent")
    private String currComponent;
    @Column(name="sourcequeue")
    private String sourceQueue;
    @Column(name="targetqueue")
    private String targetQueue;
    @Column(name="correlid")
    private String correlId;
    @Column(name="request")
    private String request;

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getCurrComponent() {
        return currComponent;
    }

    public void setCurrComponent(String currComponent) {
        this.currComponent = currComponent;
    }

    public String getSourceQueue() {
        return sourceQueue;
    }

    public void setSourceQueue(String sourceQueue) {
        this.sourceQueue = sourceQueue;
    }

    public String getTargetQueue() {
        return targetQueue;
    }

    public void setTargetQueue(String targetQueue) {
        this.targetQueue = targetQueue;
    }

    public String getCorrelId() {
        return correlId;
    }

    public void setCorrelId(String correlId) {
        this.correlId = correlId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
    
    
}
