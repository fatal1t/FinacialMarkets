/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters.sync.responses;

import fhl.main.sessionstorage.SymbolData;
import java.util.ArrayList;
import java.util.List;
import pro.xstore.api.message.records.SymbolRecord;

/**
 *
 * @author Filip
 */
public class GetAllSymbolsResponse {
    
    private final List<SymbolData> symbols;

    public GetAllSymbolsResponse() {
        this.symbols = new ArrayList<>();
    }
    
    public void AddSymbol(SymbolRecord record)
    {
        
    }

    public List<SymbolData> getSymbols() {
        return symbols;
    }
    
}
