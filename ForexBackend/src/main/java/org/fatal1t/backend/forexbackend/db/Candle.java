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
import javax.persistence.MappedSuperclass;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;

/**
 *
 * @author fatal1t
 */
@MappedSuperclass
public abstract class Candle implements Serializable {
    
    
    @Column(name = "itime")
    protected Timestamp time;
    @Column(name = "open")
    protected double open;
    @Column(name = "high")
    protected double high;
    @Column(name = "low")
    protected double low;
    @Column(name = "cclose")
    protected double close;
    @Column(name = "vol")
    protected double vol;
    @Column(name = "quoteid")
    protected int quoteId;
    @Column(name = "symbol")
    protected String symbol;
    @Column(name = "period")
    protected int period;
    

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
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

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Candle() {
    }
    public Candle(CandleDataRecord r)
    {
        this.close = r.getClose();
        this.high = r.getHigh();
        this.low = r.getLow();
        this.open = r.getOpen();
        this.quoteId = r.getQuoteId();
        this.symbol = r.getSymbol();
        this.time = Timestamp.from(Instant.ofEpochMilli(r.getCtm()));
        this.vol = r.getVol();
        this.period = r.getPeriod();
    }
    
}
