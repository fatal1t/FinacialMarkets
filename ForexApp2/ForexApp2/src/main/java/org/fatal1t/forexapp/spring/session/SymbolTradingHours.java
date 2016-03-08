/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class SymbolTradingHours {
    private final String symbol;
    private List<HourData> QuoteHours;
    private List<HourData> TradingHours;

    public SymbolTradingHours(String symbol) {
        this.symbol = symbol;
        this.QuoteHours = new ArrayList<>();
        this.TradingHours = new ArrayList<>();
    }

    public String getSymbol() {
        return symbol;
    }

    public List<HourData> getQuoteHours() {
        return QuoteHours;
    }

    public List<HourData> getTradingHours() {
        return TradingHours;
    }
    
    @Override
    public String toString()
    {
        String printableString = this.symbol+" \nOuoteHours: ";
        for(HourData data : this.QuoteHours)
        {
            printableString += data.toString()+" \n";
        }
        printableString+= "\nTradingHours: ";
        for(HourData data : this.TradingHours)
        {
            printableString += data.toString()+" \n";
        }
        return printableString;
    }
    
    
    
    
}
