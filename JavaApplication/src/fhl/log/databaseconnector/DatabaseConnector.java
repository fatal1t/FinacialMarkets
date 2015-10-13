/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.databaseconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class DatabaseConnector {
    private Connection connector;    
    public DatabaseConnector()
    {
        try {
            this.connector = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "app", "password" );
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void doTickLog(String SQL)
    {
        
    }    
    public void doCandleLog()
    {
        
    }
    public void doAuditLog()
    {
        
    }    
    public void doTradeLog()
    {
        
    }
    public void checkLogSchema()
    {
        
    }
}
