/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.eventdata;

/**
 *
 * @author Filip
 */
public class CandleDataRecord extends BaseRecord {
    private final long ctm;
    private final String ctmString;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double vol;
    private final int quoteId;
    private final String symbol;

    
    

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
}
