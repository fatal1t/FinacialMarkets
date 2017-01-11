/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.requests;

/**
 *
 * @author Filip
 */
public class CandlesRange {

    /**
     * 
     */
    private int period;
    /**
     * 
     */
    private long start;
    private long end;
    private long ticks;
    /**
     * 
     * @param period - delka candle
     * @param start - pocatecni cas pro nacteni 
     * @param end - konecny cas pro nacteni 
     * @param ticks 
     */
    public CandlesRange(int period, long start, long end, long ticks) {
        
        this.period = period;
        this.start = start;
        this.end = end;
        this.ticks = ticks;
    }
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getTicks() {
        return ticks;
    }

    public void setTicks(long ticks) {
        this.ticks = ticks;
    }
    
}
