/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.adapters.sync.responses;

/**
 *
 * @author Filip
 */
public class LoginResp {
    private final boolean isLogged;

    public LoginResp(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public boolean isIsLogged() {
        return isLogged;
    }
       
    
}
