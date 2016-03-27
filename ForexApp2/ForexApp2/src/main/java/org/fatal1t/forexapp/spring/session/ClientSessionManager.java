/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetAllSymbolsReq;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetTradingHoursReq;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetUserDataReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetAllSymbolsResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetTradingHoursResp;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetUserDataResp;
import org.fatal1t.forexapp.spring.resources.db.Symbol;
import org.fatal1t.forexapp.spring.resources.db.SymbolsRepository;
import org.fatal1t.forexapp.spring.services.common.EndpointConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service("ClientSessionManager")
public class ClientSessionManager extends EndpointConnector{
    
    private final Logger log = LogManager.getLogger(ClientSessionManager.class.getName());
    private final String connectingSymbolsQueue = "forex.sync.getallsymbols.request";
    private final String symbolsMessageType = "GetAllSymbols";
    private final String usetDataMessageType = "GetUserData";
    private final String connectingUserdataQueue = "forex.sync.getuserdata.request";
    private final String listenQueueName = "forex.session.reply";
    private final String connectingTradingHoursQueue = "forex.sync.gettradinghours.request";
    private final String tradingHoursMessageType = "GetTradingHours";
    private final XStream xs = new XStream();
    private final AppSession appSession = AppSession.getSession();
    private GetAllSymbolsResp symbolsResponse;
    
    @Autowired
    public ClientSessionManager(ConfigurableApplicationContext context) {
        super(context);
    }
    private void getSymbols()
    {
        GetAllSymbolsReq request = new GetAllSymbolsReq();
        GetAllSymbolsResp response = null;
        try{
            log.info("Sending message into queue: "+connectingSymbolsQueue);
            String pResp = request(xs.toXML(request), symbolsMessageType, connectingSymbolsQueue, listenQueueName);
            log.info("Returned message: " + pResp.substring(0, 50));
            response = (GetAllSymbolsResp) xs.fromXML(pResp);
            this.symbolsResponse = response;
        } catch (JMSException ex) {
            log.fatal("neco sa posralo na ceste do sluzby");
        }
        this.appSession.setSymbols(response.getSymbols());
        updateSymbols(response.getSymbols());
        
    }
    private void getUserData()
    {
        GetUserDataReq request = new GetUserDataReq();
        
        GetUserDataResp response = null;
        try{
            log.info("Sending message into queue: "+connectingUserdataQueue);
            String pResp = request(xs.toXML(request), usetDataMessageType, connectingUserdataQueue, listenQueueName);
            log.info("Returned message: " + pResp.substring(0, 50));
            response = (GetUserDataResp) xs.fromXML(pResp);
        } catch (JMSException ex) {
            log.fatal("neco sa posralo na ceste do sluzby");
        }
    }
    private void getTradingHours()
    {
        GetTradingHoursReq request = new GetTradingHoursReq();
        List<String> symbols = new ArrayList<>();
        symbols.add("EURUSD");
        symbols.add("EURCZK");
        request.setSymbols( symbols);
        GetTradingHoursResp response = null;
        try{
            log.info("Sending message into queue: "+ connectingTradingHoursQueue);
            String pResp = request(xs.toXML(request),  tradingHoursMessageType,  connectingTradingHoursQueue, listenQueueName);
            log.info("Returned message: " + pResp.substring(0, 50));
            response = (GetTradingHoursResp) xs.fromXML(pResp);
        } catch (JMSException ex) {
            log.fatal("neco sa posralo na ceste do sluzby");
        }
    }
    private void getTrades()
    {
        
    }
    public void init()
    {
        getUserData();
        getSymbols();
        getTradingHours();
    }
    private void updateSymbols(List<SymbolData> symbolData)
    {
        SymbolsRepository repository = this.context.getBean(SymbolsRepository.class);
        symbolData.forEach((SymbolData record )-> {
            Symbol symbol = new Symbol(record);
            List<Symbol> oldSymbols = repository.findBySymbol(symbol.getSymbol());
            if(oldSymbols.size() > 1)
            {
                repository.delete(oldSymbols);
            }
            if(oldSymbols.size() == 1)
            {
                symbol.setId(oldSymbols.get(0).getId());
            }
            repository.save(symbol);
        });
    }




    
}
