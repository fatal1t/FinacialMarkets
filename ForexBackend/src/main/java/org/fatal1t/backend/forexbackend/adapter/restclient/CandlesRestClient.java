/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.adapter.restclient;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fatal1t
 */
@Component
public class CandlesRestClient {
    
    private final Logger log = LogManager.getLogger(getClass());
    @Autowired
    private RestTemplate restTemplate;
    
    
    
    public List<CandleDataRecord> callCandlesRestService(String symbol, int period)
    {
        String url = "http://localhost:8080/ForexMQAdapter/candles/symbol/{symbol}?period={period}";
        ResponseEntity<Response> r = this.restTemplate.getForEntity(url, Response.class , symbol, period);
        log.info("Recieved " + r.getBody().getRecords().size() + " candles");
        return r.getBody().getRecords();
    }

}
