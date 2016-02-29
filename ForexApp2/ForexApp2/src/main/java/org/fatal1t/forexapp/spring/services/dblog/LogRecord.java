/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.services.dblog;



/**
 *
 * @author Filip
 */

public class LogRecord {
    /*
    a_sid character varying(40),
  a_currentcomponent varchar(40),
  a_sourcequeue character varying(40),
  a_targetqueue character varying(40),
  a_correlid character varying(40),
  a_request text
    */
    private String SID;
    private String currComponent;
    private String sourceQueue;
    private String targetQueue;
    private String correlId;
    private String request;

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
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
