/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.restapi;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.forexapp.spring.api.adapters.APIStreamingAdapter;
import org.fatal1t.forexapp.spring.api.adapters.APISyncAdapter;
import org.fatal1t.forexapp.spring.api.adapters.requests.CandlesRange;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetCandlesRangeResp;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fatal1t
 */
@RestController
public class CandlesRestApi {
    private static Logger log = LogManager.getLogger(CandlesRestApi.class);
    @Autowired
    private APISyncAdapter adapter;
     /**
     
    private int period;
    private long start;
    private long end;
    private long ticks;
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET, path = "/candles/symbol/{symbol}")
    private GetCandlesRangeResp getCandles(@PathVariable String symbol, 
            @RequestParam(required = true) int period,
            @RequestParam(required = false, defaultValue = "0") long start,
            @RequestParam(required = false, defaultValue = "0") long end) {
        log.info("Recieved request on candles " + symbol + " on period " + period);
        start = start == 0 ?  Instant.now().toEpochMilli() - 2629746000L: start;
        end = end == 0 ?  Instant.now().toEpochMilli() : end;
        CandlesRange range = new CandlesRange(period, start, end, 0);
        List<CandleDataRecord> records = adapter.getCandlesRange(symbol, range ).getRecords();
        log.info(records.get(0).toString());
        GetCandlesRangeResp response = new GetCandlesRangeResp();
        response.setRecords(records);
        return response;
    }    
}
