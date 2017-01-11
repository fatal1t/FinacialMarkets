/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.eventdata;

import pro.xstore.api.message.records.SNewsRecord;

/**
 *
 * @author Filip
 */
public class NewsRecord extends BaseRecord{
    private String body; //may not be present
    private String title;
    private String key;
    private long time;

    
    public NewsRecord()
    {
        
    }
    public NewsRecord(String body, String title, String key, long time) {
        this.body = body;
        this.title = title;
        this.key = key;
        this.time = time;
    }
    public NewsRecord(SNewsRecord record)
    {
        this(record.getBody(), record.getTitle(), record.getKey(), record.getTime());
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    
}
