/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.restapi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.forexapp.spring.api.adapters.APISyncAdapter;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetSymbolReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetAllSymbolsResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetSymbolResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController
public class SymbolsApi {
    private final Logger log = LogManager.getLogger(SymbolsApi.class);
    @Autowired
    private APISyncAdapter adapter;
    
    @RequestMapping(method = RequestMethod.GET, path = "/symbol/all")
    private GetAllSymbolsResp getSymbols()
    {
        log.info("Prijat pozadavek na seznam symbolu");
        GetAllSymbolsResp APIResp = this.adapter.GetAllSymbols();
        log.info("Adapter odpovedel ");
        return APIResp;
    }
    @RequestMapping(method = RequestMethod.GET, value= "/symbol/{symbol}")
    private GetSymbolResp getSymbol(@PathVariable String symbol)
    {
        log.info("Prijat pozadavek na symbol + " + symbol);
        GetSymbolResp resp = this.adapter.GetSymbol(new GetSymbolReq(symbol));
        log.info("Adapter odpovedel ");
        return resp;
    }
}
