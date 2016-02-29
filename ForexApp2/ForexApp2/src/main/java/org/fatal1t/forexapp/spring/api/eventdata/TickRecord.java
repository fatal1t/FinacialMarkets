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
public class TickRecord extends BaseRecord {
    	 private final double ask;
 	 private final double bid;
	 private final long askVolume;
	 private final long bidVolume;
	 private final double high;
	 private final double low;
	 private final double spreadRaw;
	 private final double spreadTable;
	 private final String symbol;
	 private final int quoteId;
	 private final int level;
	 private final long timestamp;

    public TickRecord(double ask, double bid, long askVolume, long bidVolume, 
            double high, double low, double spreadRaw, double spreadTable, 
            String symbol, int quoteId, int level, long timestamp) {
        this.ask = ask;
        this.bid = bid;
        this.askVolume = askVolume;
        this.bidVolume = bidVolume;
        this.high = high;
        this.low = low;
        this.spreadRaw = spreadRaw;
        this.spreadTable = spreadTable;
        this.symbol = symbol;
        this.quoteId = quoteId;
        this.level = level;
        this.timestamp = timestamp;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public long getAskVolume() {
        return askVolume;
    }

    public long getBidVolume() {
        return bidVolume;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getSpreadRaw() {
        return spreadRaw;
    }

    public double getSpreadTable() {
        return spreadTable;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public int getLevel() {
        return level;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
}
