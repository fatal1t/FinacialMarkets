/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.rest;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author fatal1t
 */
@RestController
public class TestingRestClient {
    private final String url = "http://localhost:9010/symbol/";
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(path = "/symbols")
    private String getSymbols()
    {
       String s = restTemplate.getForObject(url+"/all", String.class);
       return s;
    }
    @RequestMapping(path = "/symbol/{symbol}")
    private String getSymbols(@PathVariable String symbol)
    {
       String s = restTemplate.getForObject(url+"/"+symbol, String.class);
       return s;
    }
    
}
