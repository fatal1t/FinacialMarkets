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
public class GetSymbolReq {
    
    private String symbol;
    
    public GetSymbolReq()
    {
        
    }
    
    public GetSymbolReq(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
}
