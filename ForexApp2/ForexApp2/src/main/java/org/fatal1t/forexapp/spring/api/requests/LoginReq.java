/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.requests;

/**
 *
 * @author Filip
 */
public class LoginReq {
    private final String username;
    private final String password;

    public LoginReq(String username ,String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
       
    
}
