/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.sessionstorage;

/**
 *
 * @author Filip
 */
public class Session {
    
    private boolean isLogged;
    private UserData userData;

    
    public Session()
    {
        
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
    
}
