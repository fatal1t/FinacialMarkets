/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;

import org.fatal1t.forexapp.spring.session.SymbolData;
import pro.xstore.api.message.records.SymbolRecord;
import pro.xstore.api.message.response.SymbolResponse;

/**
 *
 * @author Filip
 */
public class GetSymbolResp {
    private SymbolData symbolData;
    
    public void setSymbolData(SymbolRecord record)
    {
        this.symbolData = new SymbolData(record.getAsk(), record.getBid(), record.getCurrency(),
                record.getCurrencyProfit(), record.getDescription(), record.getInstantMaxVolume(), record.getHigh(), record.getLow(),
                record.getSymbol(), record.getTime(), record.getType(), record.getGroupName(), record.getCategoryName(), record.getLongOnly(),
                record.getStarting(), record.getExpiration(), record.getStepRuleId(), record.getStopsLevel(), record.getLotMax(), record.getLotMin(),
                record.getLotStep(), record.getPrecision(), record.getContractSize(), record.getInitialMargin(), 
                record.getMarginHedged(), record.getMarginHedgedStrong(), record.getMarginMaintenance(), record.getMarginMode().getCode(), record.getPercentage(),
                record.getProfitMode().getCode(), record.getSpreadRaw(), record.getSpreadTable(),  record.getSwapLong(), record.getSwapShort(), 
                record.getSwapType().getCode(), record.getSwap_rollover().getCode(), record.getTickSize(), record.getTickValue(), record.getQuoteId(), record.getTimeString(),
                record.getLeverage());
        
    }

    public SymbolData getSymbolData() {
        return symbolData;
    }
    
}
