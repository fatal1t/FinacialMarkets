/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.resources.db;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "candlestemp", schema = "forexdata")
public class Candle implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "forexdata.candelstempids")
    @SequenceGenerator(name = "forexdata.candelstempids", sequenceName = "forexdata.candelstempids")
    @Column(name= "id", insertable = false, updatable = false)
    private long id;
    
    @Column(name = "itime")
    private Timestamp time;
    
    @Column(name = "copen")
    private double open;
    
    @Column(name = "chigh")
    private double high;
    
    @Column(name = "clow")
    private double low;
    
    @Column(name = "cclose")
    private double close;
    @Column(name = "cvol")
    private double vol;
    @Column(name = "cquoteid")
    private int quoteId;
    @Column(name = "csymbol")
    private String symbol;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
    
    
}
