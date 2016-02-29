/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;


import java.util.ArrayList;
import java.util.List;
import org.fatal1t.forexapp.session.SymbolTradingHours;

/**
 *
 * @author Filip
 */
public class GetTradingHoursResponse {
    private List<SymbolTradingHours> symbolTradingHoursList;

    public List<SymbolTradingHours> getSymbolTradingHoursList() {
        return symbolTradingHoursList;
    }

    public GetTradingHoursResponse() {
        this.symbolTradingHoursList = new ArrayList<>();
    }
    
    
    
    public void addSymbolTradingHours(SymbolTradingHours symbolTradingHours)
    {
        symbolTradingHoursList.add(symbolTradingHours);
    }
    @Override
    public String toString()
    {
        String printableString = "";
        for(SymbolTradingHours hours : this.symbolTradingHoursList)
        {
            printableString+=hours.toString()+"\n";
        }
        return printableString;
        
    }
}
