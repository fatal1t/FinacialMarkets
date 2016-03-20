/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.resources.db;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.fatal1t.forexapp.spring.session.SymbolData;

/**
 *
 * @author Filip
 */
@Entity
@Table(name = "symbols", schema = "forexdata")

public class Symbol implements Serializable {
    
    @Id
    @SequenceGenerator(name="idgen", sequenceName = "forexdata.symbolsids")
    @GeneratedValue(generator = "idgen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;
    
    @Column(name="ask")
    private double ask;
    
    @Column(name="bid")
    private double bid;

    @Column(name="currency")
    private String currency;
    
    @Column(name="currencypfoit")
    private String currencyProfit;
    
    @Column(name = "description")
    private String description;
    
    @Column(name="instantmaxvolume")
    private int instantMaxVolume;
    
    @Column(name="high")
    private double high;
    
    
    @Column(name="low")
    private double low;
    
    @Column(name="symbol")
    private String symbol;
    
    @Column(name="itime")
    private Timestamp time;
    
    @Column(name ="itype")
    private int type;

    @Column(name="groupname")
    private String groupName;
    
    @Column(name="categoryname")
    private String categoryName;
    
    @Column(name="bigintonly")
    private boolean longOnly;
    
    @Column(name="starting")
    private long starting;
    
    @Column(name="expiration")
    private long expiration;
    
    @Column(name="stepruleid")
    private int stepRuleId;
    
    @Column(name="stopslevel")
    private int stopsLevel;
    
    @Column(name="lotmax")
    private double lotMax;
    
    @Column(name="lotmin")
    private double lotMin;
    
    @Column(name="lotstep")
    private double lotStep;

    @Column(name="iprecision")
    private int precision;
    
    @Column(name="cotranctsize")
    private long contractSize;
    
    @Column(name="initialmargin")
    private long initialMargin;
    
    @Column(name="marginhedged")
    private double marginHedged;
    
    @Column(name="marginhedgedstrong")
    private boolean marginHedgedStrong;
    
    @Column(name="marginmaintanence")
    private long marginMaintenance;
    
    @Column(name="marginmode")
    private long marginMode;
    
    @Column(name="percentage")
    private double percentage;
    
    @Column(name="profitmode")
    private long profitMode;
    
    @Column(name="spreadraw")
    private double spreadRaw;
    
    @Column(name="spreadtable")
    private double spreadTable;
    
    @Column(name="swapbigint")
    private double swapLong;
    
    @Column(name="swapshort")
    private double swapShort;
    
    @Column(name="swaptype")
    private long swapType;
    
    @Column(name="swaprollover")
    private long swapRollover;
    
    @Column(name="ticksize")
    private double tickSize;
    
    @Column(name="tickvalue")
    private double tickValue;
    
    @Column(name="quoteid")
    private int quoteId;
    
    @Column(name="leverage")
    private double leverage;
    
    @Column(name="ticks")
    private boolean ticks;
        
    public Symbol()
    {
        
    }
    
    public Symbol(SymbolData record)
    {
        this.ask = record.getAsk();
        this.bid = record.getBid();
        this.categoryName = record.getCategoryName();
        this.contractSize = record.getContractSize();
        this.currency = record.getCurrency();
        this.currencyProfit = record.getCurrencyProfit();
        this.description = record.getDescription();
        this.expiration = record.getExpiration();
        this.groupName = record.getGroupName();
        this.high = record.getHigh();
        this.initialMargin = record.getInitialMargin();
        this.instantMaxVolume = record.getInstantMaxVolume();
        this.leverage = record.getLeverage();
        this.longOnly = record.isLongOnly();
        this.lotMax = record.getLotMax();
        this.lotMin = record.getLotMin();
        this.lotStep = record.getLotStep();
        this.low = record.getLow();
        this.marginHedged = record.getMarginHedged();
        this.marginHedgedStrong = record.isMarginHedgedStrong();
        this.marginMaintenance = record.getMarginMaintenance();
        this.marginMode = record.getMarginMode();
        this.percentage = record.getPercentage();
        this.precision = record.getPrecision();
        this.profitMode = record.getProfitMode();
        this.quoteId = record.getQuoteId();
        this.spreadRaw = record.getSpreadRaw();
        this.spreadTable = record.getSpreadTable();
        this.starting = record.getStarting();
        this.stepRuleId = record.getStepRuleId();
        this.stopsLevel = record.getStopsLevel();
        this.swapLong = record.getSwapLong();
        this.swapRollover = record.getSwapRollover();
        this.swapShort = record.getSwapShort();
        this.swapType = record.getSwapType();
        this.symbol = record.getSymbol();
        this.tickSize = record.getTickSize();
        this.tickValue = record.getTickValue();
        this.time =  Timestamp.from(Instant.ofEpochMilli(record.getTime()));
        this.type = record.getType();
        if(this.categoryName.equals("Forex"))
        {
            this.ticks = true;
        }
        else
        {
            this.ticks = false;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyProfit() {
        return currencyProfit;
    }

    public void setCurrencyProfit(String currencyProfit) {
        this.currencyProfit = currencyProfit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInstantMaxVolume() {
        return instantMaxVolume;
    }

    public void setInstantMaxVolume(int instantMaxVolume) {
        this.instantMaxVolume = instantMaxVolume;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isLongOnly() {
        return longOnly;
    }

    public void setLongOnly(boolean longOnly) {
        this.longOnly = longOnly;
    }

    public long getStarting() {
        return starting;
    }

    public void setStarting(long starting) {
        this.starting = starting;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public int getStepRuleId() {
        return stepRuleId;
    }

    public void setStepRuleId(int stepRuleId) {
        this.stepRuleId = stepRuleId;
    }

    public int getStopsLevel() {
        return stopsLevel;
    }

    public void setStopsLevel(int stopsLevel) {
        this.stopsLevel = stopsLevel;
    }

    public double getLotMax() {
        return lotMax;
    }

    public void setLotMax(double lotMax) {
        this.lotMax = lotMax;
    }

    public double getLotMin() {
        return lotMin;
    }

    public void setLotMin(double lotMin) {
        this.lotMin = lotMin;
    }

    public double getLotStep() {
        return lotStep;
    }

    public void setLotStep(double lotStep) {
        this.lotStep = lotStep;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public long getContractSize() {
        return contractSize;
    }

    public void setContractSize(long contractSize) {
        this.contractSize = contractSize;
    }

    public long getInitialMargin() {
        return initialMargin;
    }

    public void setInitialMargin(long initialMargin) {
        this.initialMargin = initialMargin;
    }

    public double getMarginHedged() {
        return marginHedged;
    }

    public void setMarginHedged(double marginHedged) {
        this.marginHedged = marginHedged;
    }

    public boolean isMarginHedgedStrong() {
        return marginHedgedStrong;
    }

    public void setMarginHedgedStrong(boolean marginHedgedStrong) {
        this.marginHedgedStrong = marginHedgedStrong;
    }

    public long getMarginMaintenance() {
        return marginMaintenance;
    }

    public void setMarginMaintenance(long marginMaintenance) {
        this.marginMaintenance = marginMaintenance;
    }

    public long getMarginMode() {
        return marginMode;
    }

    public void setMarginMode(long marginMode) {
        this.marginMode = marginMode;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public long getProfitMode() {
        return profitMode;
    }

    public void setProfitMode(long profitMode) {
        this.profitMode = profitMode;
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

    public double getSwapLong() {
        return swapLong;
    }

    public void setSwapLong(double swapLong) {
        this.swapLong = swapLong;
    }

    public double getSwapShort() {
        return swapShort;
    }

    public void setSwapShort(double swapShort) {
        this.swapShort = swapShort;
    }

    public long getSwapType() {
        return swapType;
    }

    public void setSwapType(long swapType) {
        this.swapType = swapType;
    }

    public long getSwapRollover() {
        return swapRollover;
    }

    public void setSwapRollover(long swapRollover) {
        this.swapRollover = swapRollover;
    }

    public double getTickSize() {
        return tickSize;
    }

    public void setTickSize(double tickSize) {
        this.tickSize = tickSize;
    }

    public double getTickValue() {
        return tickValue;
    }

    public void setTickValue(double tickValue) {
        this.tickValue = tickValue;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }

    public double getLeverage() {
        return leverage;
    }

    public void setLeverage(double leverage) {
        this.leverage = leverage;
    }

    public boolean isTicks() {
        return ticks;
    }

    public void setTicks(boolean ticks) {
        this.ticks = ticks;
    }

    
    
}
