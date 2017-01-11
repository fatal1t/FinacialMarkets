/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.restapi;

import org.fatal1t.forexapp.spring.api.eventdata.NewTradeRecord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController(value = "/trades")
public class TradesRestApi {
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    private void getAllTrades()
    {
        
    }
    @RequestMapping(value = "/{trade_id}", method = RequestMethod.GET)
    private void getTrade(@PathVariable String tradeId )
    {
        
    }
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    private void createNewTrade(@RequestBody NewTradeRecord tradeRecord)
    {
        
    }
}
