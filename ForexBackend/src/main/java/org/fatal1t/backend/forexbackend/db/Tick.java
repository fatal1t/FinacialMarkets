/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.db;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.fatal1t.forexapp.spring.api.eventdata.TickRecord;

/**
 *
 * @author fatal1t
 * 
 * CREATE TABLE forexdata.ticks_eurusd
(
  id bigint DEFAULT nextval('forexdata.ticks_eur_usd_ids'),
  ask double precision,
  bid double precision,
  askvolume bigint,
  bidvolume bigint,
  high double precision,
  low double precision,
  spreadraw double precision,
  spreadtable double precision,
  symbol varchar(3),
  quoteid integer,
  ilevel integer,
  itime timestamp without time zone,
  CONSTRAINT ticks_eurusd_id PRIMARY KEY (id)
)
 */
@MappedSuperclass
public abstract class Tick implements Serializable{
  
    @Column(name="ask")
    protected double ask;
    
    @Column(name="bid")
    protected double bid;
    
    @Column(name="askvolume")
    protected Long askVolume;
    
    @Column(name="bidvolume")
    protected Long bidVolume;
    
    @Column(name="high")
    protected double high;
    
    @Column(name="low")
    protected double low;
    
    @Column(name="spreadraw")
    protected double spreadRaw;
    
    @Column(name="spreadtable")
    protected double spreadTable;
    
    @Column(name="symbol")
    protected String symbol;
    
    @Column(name="quoteid")
    protected int quoteId;
    
    @Column(name="ilevel")
    protected int level;
    
    @Column(name="itime")
    protected Timestamp time;

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public Long getAskVolume() {
        return askVolume;
    }

    public void setAskVolume(Long askVolume) {
        this.askVolume = askVolume;
    }

    public Long getBidVolume() {
        return bidVolume;
    }

    public void setBidVolume(Long bidVolume) {
        this.bidVolume = bidVolume;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getSpreadRaw() {
        return spreadRaw;
    }

    public void setSpreadRaw(double spreadRaw) {
        this.spreadRaw = spreadRaw;
    }

    public double getSpreadTable() {
        return spreadTable;
    }

    public void setSpreadTable(double spreadTable) {
        this.spreadTable = spreadTable;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Tick() {
    }

    
    public Tick(TickRecord r)
    {
        this.ask = r.getAsk();
        this.bid = r.getBid();
        this.askVolume = r.getAskVolume();
        this.bidVolume = r.getBidVolume();
        this.high = r.getHigh();
        this.low = r.getLow();
        this.spreadRaw = r.getSpreadRaw();
        this.spreadTable = r.getSpreadTable();
        this.symbol = r.getSymbol();
        this.quoteId = r.getQuoteId();
        this.level = r.getLevel();
        this.time = Timestamp.from(Instant.ofEpochMilli(r.getTimestamp()));
    }
    
}   
