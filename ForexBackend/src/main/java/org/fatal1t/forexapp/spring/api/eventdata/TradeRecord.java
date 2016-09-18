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
public class TradeRecord extends BaseRecord {

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

    public TradeRecord(long type, String state, long open_time, Double close_price, boolean closed, int cmd, String comment, String customComment, Double commission, 
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

    public TradeRecord()
    {
        
    }


    public long getStatus() {
        return type;
    }

    public void setStatus(long status) {
        this.type = status;
    }
    
}
