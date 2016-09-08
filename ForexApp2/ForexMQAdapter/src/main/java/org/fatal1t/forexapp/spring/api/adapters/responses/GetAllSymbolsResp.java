/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.responses;


import java.util.ArrayList;
import java.util.List;
import org.fatal1t.forexapp.spring.session.SymbolData;
import pro.xstore.api.message.records.SymbolRecord;

/**
 *
 * @author Filip
 */
public class GetAllSymbolsResp {
    
    private final List<SymbolData> symbols;

    public GetAllSymbolsResp() {
        this.symbols = new ArrayList<>();
    }
    
    public void AddSymbol(SymbolRecord record)
    {
        SymbolData data = new SymbolData(record.getAsk(), record.getBid(), record.getCurrency(),
                record.getCurrencyProfit(), record.getDescription(), record.getInstantMaxVolume(), record.getHigh(), record.getLow(),
                record.getSymbol(), record.getTime(), record.getType(), record.getGroupName(), record.getCategoryName(), record.getLongOnly(),
                record.getStarting(), record.getExpiration(), record.getStepRuleId(), record.getStopsLevel(), record.getLotMax(), record.getLotMin(),
                record.getLotStep(), record.getPrecision(), record.getContractSize(), record.getInitialMargin(), 
                record.getMarginHedged(), record.getMarginHedgedStrong(), record.getMarginMaintenance(), record.getMarginMode().getCode(), record.getPercentage(),
                record.getProfitMode().getCode(), record.getSpreadRaw(), record.getSpreadTable(),  record.getSwapLong(), record.getSwapShort(), 
                record.getSwapType().getCode(), record.getSwap_rollover().getCode(), record.getTickSize(), record.getTickValue(), record.getQuoteId(), record.getTimeString(),
                record.getLeverage());
        this.symbols.add(data);
    }

    public List<SymbolData> getSymbols() {
        return symbols;
    }
    public List<String> getSymbolsList()
    {
        List<String> list = new ArrayList<>();
        this.symbols.forEach((SymbolData item) -> {
           list.add(item.getSymbol());
        });
        return list;
    }
}
