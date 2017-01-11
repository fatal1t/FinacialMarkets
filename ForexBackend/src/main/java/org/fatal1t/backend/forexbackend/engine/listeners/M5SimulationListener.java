/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.listeners;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class M5SimulationListener implements UpdateListener {

    //{symbol=EURUSD, high=1.12349, vol=2993.0, low=1.1228, close=1.12342, ctm=1473713880000, ctmString=1473713880000, open=1.12334, quoteId=2}

    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        System.out.println(newEvents[0].getEventType().getUnderlyingType().getName());
        Long ctm = (Long) newEvents[0].get("ctm");
        String ctmString = (String) newEvents[0].get("ctmString");
        Double high =  (Double) newEvents[0].get("high");
        Double low =  (Double) newEvents[0].get("low");
        Double open =  (Double) newEvents[0].get("open");
        Double close =  (Double) newEvents[0].get("close");
        Double vol = (Double) newEvents[0].get("vol");
        String symbol =  (String) newEvents[0].get("symbol");                        
        int quoteId = (Integer) newEvents[0].get("quoteId");
        System.out.println("New M5 candle: high " +high + " low: " + low + " open: " + open + " close: "+ close + " symbol: "+ symbol);
        //this.candleService.saveCalculatedCandle(new CandleDataRecord(ctm, ctmString, open, high, low, close, vol, quoteId, symbol, 5));
    }
}
