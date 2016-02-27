/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.session;

/**
 *
 * @author Filip
 */
public class SymbolData {
    private final double ask;
    private final double bid;
    private final String currency;
    private final String currencyProfit;
    private final String description;
    private final int instantMaxVolume;
    private final double high;
    private final double low;
    private final String symbol;
    private final long time;
    private final int type;
    private final String groupName;
    private final String categoryName;
    private final boolean longOnly;
    private final long starting;
    private final long expiration;
    private final int stepRuleId;
    private final int stopsLevel;
    private final double lotMax;
    private final double lotMin;
    private final double lotStep;
    private final int precision;
    private final long contractSize;
    private final long initialMargin;
    private final double marginHedged;
    private final boolean marginHedgedStrong;
    private final long marginMaintenance;
    private final long marginMode;
    private final double percentage;
    private final long profitMode;
    private final double spreadRaw;
    private final double spreadTable;
    private final double swapLong;
    private final double swapShort;
    private final long swapType;
    private final long swapRollover;
    private final double tickSize;
    private final double tickValue;
    private final int quoteId;
    private final String timeString;
    private final double leverage;

    public SymbolData(double ask, double bid, String currency, String currencyProfit, String description, 
            int instantMaxVolume, double high, double low, String symbol, long time, int type, 
            String groupName, String categoryName, boolean longOnly, Long starting, Long expiration, 
            int stepRuleId, int stopsLevel, double lotMax, double lotMin, double lotStep, int precision, 
            Long contractSize, Long initialMargin, double marginHedged, boolean marginHedgedStrong, 
            Long marginMaintenance, long marginMode, double percentage, long profitMode, double spreadRaw, 
            double spreadTable, double swapLong, double swapShort, long swapType, 
            long swapRollover, double tickSize, double tickValue, int quoteId, String timeString, 
            double leverage) {
        this.ask = ask;
        this.bid = bid;
        this.currency = currency;
        this.currencyProfit = currencyProfit;
        this.description = description;
        this.instantMaxVolume = instantMaxVolume;
        this.high = high;
        this.low = low;
        this.symbol = symbol;
        this.time = time;
        this.type = type;
        this.groupName = groupName;
        this.categoryName = categoryName;
        this.longOnly = longOnly;
        this.starting = starting == null?  -1 : starting;
        this.expiration = expiration == null ? -1 : expiration;
        this.stepRuleId = stepRuleId;
        this.stopsLevel = stopsLevel;
        this.lotMax = lotMax;
        this.lotMin = lotMin;
        this.lotStep = lotStep;
        this.precision = precision;
        this.contractSize = contractSize;
        this.initialMargin = initialMargin;
        this.marginHedged = marginHedged;
        this.marginHedgedStrong = marginHedgedStrong;
        this.marginMaintenance = marginMaintenance;
        this.marginMode = marginMode;
        this.percentage = percentage;
        this.profitMode = profitMode;
        this.spreadRaw = spreadRaw;
        this.spreadTable = spreadTable;
        this.swapLong = swapLong;
        this.swapShort = swapShort;
        this.swapType = swapType;
        this.swapRollover = swapRollover;
        this.tickSize = tickSize;
        this.tickValue = tickValue;
        this.quoteId = quoteId;
        this.timeString = timeString;
        this.leverage = leverage;
    }

    public double getAsk() {
        return ask;
    }

    public double getBid() {
        return bid;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCurrencyProfit() {
        return currencyProfit;
    }

    public String getDescription() {
        return description;
    }

    public int getInstantMaxVolume() {
        return instantMaxVolume;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getTime() {
        return time;
    }

    public int getType() {
        return type;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isLongOnly() {
        return longOnly;
    }

    public Long getStarting() {
        return starting;
    }

    public Long getExpiration() {
        return expiration;
    }

    public int getStepRuleId() {
        return stepRuleId;
    }

    public int getStopsLevel() {
        return stopsLevel;
    }

    public double getLotMax() {
        return lotMax;
    }

    public double getLotMin() {
        return lotMin;
    }

    public double getLotStep() {
        return lotStep;
    }

    public int getPrecision() {
        return precision;
    }

    public Long getContractSize() {
        return contractSize;
    }

    public Long getInitialMargin() {
        return initialMargin;
    }

    public double getMarginHedged() {
        return marginHedged;
    }

    public boolean isMarginHedgedStrong() {
        return marginHedgedStrong;
    }

    public Long getMarginMaintenance() {
        return marginMaintenance;
    }

    public long getMarginMode() {
        return marginMode;
    }

    public double getPercentage() {
        return percentage;
    }

    public long getProfitMode() {
        return profitMode;
    }

    public double getSpreadRaw() {
        return spreadRaw;
    }

    public double getSpreadTable() {
        return spreadTable;
    }

    public double getSwapLong() {
        return swapLong;
    }

    public double getSwapShort() {
        return swapShort;
    }

    public long getSwapType() {
        return swapType;
    }

    public long getSwapRollover() {
        return swapRollover;
    }

    public double getTickSize() {
        return tickSize;
    }

    public double getTickValue() {
        return tickValue;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public String getTimeString() {
        return timeString;
    }

    public double getLeverage() {
        return leverage;
    }

    @Override
    public String toString()
    {
        return "Symbol: " + this.symbol + " ask: "+ Double.toString(this.ask) + " bid: " + Double.toString(bid);
    }
    

    
}
