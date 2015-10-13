/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.log.databaseconnector;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class DatabaseConnectorPool implements IDatabaseConnectorPool<DatabaseConnector>{
    private final List<DatabaseConnector> pool;
    private static DatabaseConnectorPool instance;
    private int instanceNumber;
    
    private DatabaseConnectorPool()
    {
        this.instanceNumber = 10;
        this.pool = new ArrayList<>();
        for(int i = 0; i<10;i++)
        {
            this.pool.add(new DatabaseConnector());
        }        
    }

    @Override
    public DatabaseConnector getInstance() {
        while(this.pool.isEmpty()|| this.instanceNumber == 0)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(DatabaseConnectorPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.instanceNumber--;
        DatabaseConnector conn = this.pool.get(0);
        this.pool.remove(0);
        return conn;        
    }

    @Override
    public void returnInstance(DatabaseConnector instance) {
        this.pool.add(instance);
        this.instanceNumber++;
    }
    public static DatabaseConnectorPool getPoolInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseConnectorPool();
            
        }
        return instance;
    }
    
}
