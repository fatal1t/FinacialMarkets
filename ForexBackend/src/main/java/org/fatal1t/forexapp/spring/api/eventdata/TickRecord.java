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
    private  double ask;
    private  double bid;
    private  long askVolume;
    private  long bidVolume;
    private  double high;
    private  double low;
    private  double spreadRaw;
    private  double spreadTable;
    private  String symbol;
    private  int quoteId;
    private  int level;
    private  long timestamp;
    
    public TickRecord()
    {
        
    }

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
    
    @Override
    public String toString()
    {
    return "Tick: [symbol: "+getSymbol()+ ", time: "+getTimestamp()+" ]";
    }
}
