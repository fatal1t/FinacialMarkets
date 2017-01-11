/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.backend.forexbackend.services;

import org.fatal1t.backend.forexbackend.engine.listeners.BuyRealtimeListener;
import org.fatal1t.backend.forexbackend.engine.listeners.BuySimulationListener;
import org.fatal1t.backend.forexbackend.engine.listeners.SellRealTimeListener;
import org.fatal1t.backend.forexbackend.engine.listeners.SellSimulationListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author fatal1t
 */
@Service
public class TradeService implements BuyRealtimeListener.TradeSerivceInterface, 
        SellSimulationListener.TradeSerivceInterface, BuySimulationListener.TradeSerivceInterface, SellRealTimeListener.TradeSerivceInterface{

    @Override
    public void makeTrade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTrades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
