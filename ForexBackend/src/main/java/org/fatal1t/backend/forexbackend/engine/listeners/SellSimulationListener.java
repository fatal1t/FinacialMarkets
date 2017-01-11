/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.engine.listeners;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author fatal1t
 */
@Component
public class SellSimulationListener implements StatementAwareUpdateListener {
    private final TradeSerivceInterface tradeService;
    
    @Autowired
    public SellSimulationListener(TradeSerivceInterface tradeService)
    {
        this.tradeService = tradeService;
    }
    @Override
    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement statement, EPServiceProvider epServiceProvider) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public interface TradeSerivceInterface
    {
        public void makeTrade();
        public void getTrades();
        
    }
}
