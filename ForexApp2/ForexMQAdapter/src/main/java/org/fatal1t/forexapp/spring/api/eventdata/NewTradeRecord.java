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
public class NewTradeRecord {
    private String cmd;
    private String type;
    private Double price;
    private Double sl;
    private Double tp;
    private String symbol;
    private Double volume;
    private Long order;
    private String customComment;
    private Long expiration;

    public NewTradeRecord() {
    }

    public NewTradeRecord(String cmd, String type, Double price, Double sl, Double tp, String symbol, Double volume, Long order, String customComment, Long expiration) {
        this.cmd = cmd;
        this.type = type;
        this.price = price;
        this.sl = sl;
        this.tp = tp;
        this.symbol = symbol;
        this.volume = volume;
        this.order = order;
        this.customComment = customComment;
        this.expiration = expiration;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getCustomComment() {
        return customComment;
    }

    public void setCustomComment(String customComment) {
        this.customComment = customComment;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
    
    
}
