/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.session;

import java.util.List;
import org.springframework.stereotype.Repository;
import pro.xstore.api.sync.ServerData;
import pro.xstore.api.sync.ServerData.ServerEnum;

/**
 *
 * @author Filip
 */
@Repository
public class Configuration {
    private final String username;
    private final String password; 
    private final ServerEnum serverType;
    private boolean isLogged;
    private List<String> symbols;
    private List<String> usedSymbols;
    public Configuration()
    {
        this.username = "769180";
        this.password = "5fe8a658";
        this.serverType = ServerData.ServerEnum.DEMO;
        
    }
    public Configuration(String user, String password, String Server, boolean isLogged)
    {
        this.username = "769180";
        this.password = "5fe8a658";
        this.serverType = ServerData.ServerEnum.DEMO;
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ServerEnum getServerType() {
        return serverType;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public List<String> getUsedSymbols() {
        return usedSymbols;
    }
    
}
