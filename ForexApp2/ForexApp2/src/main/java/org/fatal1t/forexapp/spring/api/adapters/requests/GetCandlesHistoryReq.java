/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.requests;

import java.util.List;

/**
 *
 * @author Filip
 */
public class GetCandlesHistoryReq {
    private String symbol;
    private List<CandlesRange> requestList;

    public GetCandlesHistoryReq(String symbol, List<CandlesRange> requestList) {
        this.symbol = symbol;
        this.requestList = requestList;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public List<CandlesRange> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<CandlesRange> requestList) {
        this.requestList = requestList;
    }
    
}
