/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.calculations.messaging;

import com.thoughtworks.xstream.XStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import org.fatal1t.forexapp.spring.api.adapters.requests.CandlesRange;
import org.fatal1t.forexapp.spring.api.adapters.requests.GetCandlesHistoryReq;
import org.fatal1t.forexapp.spring.api.adapters.responses.GetCandlesHistoryResp;
import org.fatal1t.forexapp.spring.api.eventdata.CandleDataRecord;
import org.fatal1t.forexapp.spring.services.common.EndpointConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service
public class CalculationConnector extends EndpointConnector {

    private final String candlesHistoryQueue = "forex.sync.getcandleshistory.request";
    private final String replyToQueue = "forex.candlestorage.reply";
    private final XStream xs = new XStream();
    
    @Autowired
    public CalculationConnector(ConfigurableApplicationContext context) {
        super(context);
    }
    public HashMap<Integer, List<CandleDataRecord>> getCandlesRange(String symbol, List<CandlesRange> candlesRanges)
    {
        
        try {
            GetCandlesHistoryReq request = new GetCandlesHistoryReq(symbol, candlesRanges);
            String strResponse = this.request(xs.toXML(request), "GetCandlesRange", candlesHistoryQueue, replyToQueue);
            GetCandlesHistoryResp response = (GetCandlesHistoryResp) this.xs.fromXML(strResponse);
            return response.getRecords();
        } catch (JMSException ex) {
            Logger.getLogger(CalculationConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
