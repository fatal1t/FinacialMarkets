/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import java.util.List;


/**
 *
 * @author Filip
 */


public class AppSession implements SessionLocal {
    private boolean isLoaded;
    private UserData userData;
    private List<SymbolData> symbols;
    private SymbolTradingHours tradingHours;
    private Configuration config = new Configuration();
    static private AppSession session;

    @Override
    public void readConfiguration() {
        //To change body of generated methods, choose Tools | Templates.
        
    }
    static public AppSession getSession()
    {
        if(session == null)
        {
            session = new AppSession();
        }
        return session;
    }
    
    private AppSession()
    {
        
    }

    @Override
    public Configuration getConfiguration() {
        if(this.config != null)
        {
            System.out.println(this.config.getUsername());
            return this.config;
        }
        else
        {
            System.out.println(this.config.getUsername());
            this.config = new Configuration();
            return this.config;
            
        } //To change body of generated methods, choose Tools | Templates.
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public boolean isIsLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public List<SymbolData> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolData> symbols) {
        this.symbols = symbols;
    }

    public SymbolTradingHours getTradingHours() {
        return tradingHours;
    }

    public void setTradingHours(SymbolTradingHours tradingHours) {
        this.tradingHours = tradingHours;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
    
    
}
