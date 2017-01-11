/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.eventdata;

/**
 *
 * @author fatal1t
 */
public class StreamingTradeRecord {
    private long type;
    private String state;
    private long open_time;
    private Double close_price;
    private boolean closed;
    private int cmd;
    private String comment;
    private String customComment;
    private Double commission;
    private long order;
    private long order2;
    private double volume;
    private Double margin_rate;
    private Double open_price;
    private String symbol;
    private Double storage;
    private int digits;
    private Long close_time;
    private Long expiration;
    private long position;
    private Double profit;
    private Double sl;
    private Double tp;

    public StreamingTradeRecord(long type, String state, long open_time, Double close_price, boolean closed, int cmd, String comment, String customComment, Double commission, 
            long order, long order2, double volume, Double margin_rate, Double open_price, String symbol, Double storage, int digits, Long close_time, Long expiration, 
            long position, Double profit, Double sl, Double tp) {
        this.type = type;
        this.state = state;
        this.open_time = open_time;
        this.close_price = close_price;
        this.closed = closed;
        this.cmd = cmd;
        this.comment = comment;
        this.customComment = customComment;
        this.commission = commission;
        this.order = order;
        this.order2 = order2;
        this.volume = volume;
        this.margin_rate = margin_rate;
        this.open_price = open_price;
        this.symbol = symbol;
        this.storage = storage;
        this.digits = digits;
        this.close_time = close_time;
        this.expiration = expiration;
        this.position = position;
        this.profit = profit;
        this.sl = sl;
        this.tp = tp;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getOpen_time() {
        return open_time;
    }

    public void setOpen_time(long open_time) {
        this.open_time = open_time;
    }

    public Double getClose_price() {
        return close_price;
    }

    public void setClose_price(Double close_price) {
        this.close_price = close_price;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomComment() {
        return customComment;
    }

    public void setCustomComment(String customComment) {
        this.customComment = customComment;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getOrder2() {
        return order2;
    }

    public void setOrder2(long order2) {
        this.order2 = order2;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public Double getMargin_rate() {
        return margin_rate;
    }

    public void setMargin_rate(Double margin_rate) {
        this.margin_rate = margin_rate;
    }

    public Double getOpen_price() {
        return open_price;
    }

    public void setOpen_price(Double open_price) {
        this.open_price = open_price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getStorage() {
        return storage;
    }

    public void setStorage(Double storage) {
        this.storage = storage;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public Long getClose_time() {
        return close_time;
    }

    public void setClose_time(Long close_time) {
        this.close_time = close_time;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

   
    
}
