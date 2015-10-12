/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.sessionstorage;

import java.util.List;

/**
 *
 * @author Filip
 */
public class Session {
    private final String username;
    private final String password; 
    private final String serverType;
    private boolean isLogged;
    private UserData userData;
    private List<SymbolData> symbols;

    
    public Session(String username, String password, String serverType)
    {
        this.username = username;
        this.password = password;
        this.serverType = serverType;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    

    public void Init() {
        

    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setSymbols(List<SymbolData> symbols) {
        this.symbols = symbols;
    }

    public List<SymbolData> getSymbols() {
        return symbols;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getServerType() {
        return serverType;
    }
    
}
