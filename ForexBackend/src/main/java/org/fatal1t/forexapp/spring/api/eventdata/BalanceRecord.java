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
public class BalanceRecord extends BaseRecord {
    
    private double balance;
    private double margin;
    private double equity;
    private double marginLevel;
    private double marginFree;
    private double credit;

    public BalanceRecord()
    {
        
    }
    public BalanceRecord(double balance, double margin, double equity, double marginLevel, double marginFree, double credit) {
        this.balance = balance;
        this.margin = margin;
        this.equity = equity;
        this.marginLevel = marginLevel;
        this.marginFree = marginFree;
        this.credit = credit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getEquity() {
        return equity;
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }

    public double getMarginLevel() {
        return marginLevel;
    }

    public void setMarginLevel(double marginLevel) {
        this.marginLevel = marginLevel;
    }

    public double getMarginFree() {
        return marginFree;
    }

    public void setMarginFree(double marginFree) {
        this.marginFree = marginFree;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
