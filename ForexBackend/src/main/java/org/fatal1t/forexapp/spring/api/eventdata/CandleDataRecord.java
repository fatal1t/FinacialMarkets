/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.eventdata;

import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author Filip
 */
public class CandleDataRecord extends BaseRecord {
    private long ctm;
    private  String ctmString;
    private  double open;
    private  double high;
    private  double low;
    private  double close;
    private  double vol;
    private  int quoteId;
    private  String symbol;
    private int period;

    public CandleDataRecord()
    {
        
    }    
    /**
     * Defaultne se nastavuje perioda = 1, jinak je nutno vyuzit druhy konstruktor.
     * @param ctm
     * @param ctmString
     * @param open
     * @param high
     * @param low
     * @param close
     * @param vol
     * @param quoteId
     * @param symbol 
     */
    public CandleDataRecord(long ctm, String ctmString, double open, double high, double low, double close, double vol, int quoteId, String symbol) {
        this.ctm = ctm;
        this.ctmString = ctmString;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol; 
        this.quoteId = quoteId;
        this.symbol = symbol;
        this.period = 1;
    }
    public CandleDataRecord(long ctm, String ctmString, double open, double high, double low, double close, double vol, int quoteId, String symbol, int period) {
        this.ctm = ctm;
        this.ctmString = ctmString;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol; 
        this.quoteId = quoteId;
        this.symbol = symbol;
    }

    public long getCtm() {
        return ctm;
    }

    public String getCtmString() {
        return ctmString;
    }

    public double getOpen() {
        return open;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getVol() {
        return vol;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
    
    @Override
    public String toString()
    {
        return "Candle: symbol: "+ this.symbol + " period: " + this.period + " timestamp: "+Timestamp.from(Instant.ofEpochMilli(this.ctm)).toString();
    }
}
