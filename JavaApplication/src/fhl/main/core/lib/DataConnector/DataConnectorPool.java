/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.lib.DataConnector;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class DataConnectorPool {
    public List<DataConnector> connectoList;
    int connNumber;
    private static DataConnectorPool pool;
    
    private DataConnectorPool()
    {
        this.connectoList = new ArrayList<>();
        this.connNumber = 10;
        for(int i =0; i< this.connNumber; i++)
        {
            this.connectoList.add(new DataConnector());
        }
    }
    public static DataConnectorPool getInstance()
    {
        if(pool == null)
        {
            pool = new DataConnectorPool();
        }
        return pool;
    }
    public DataConnector getConnector()
    {
        if(connNumber != 0)
        {
            this.connNumber--;
            return this.connectoList.remove(0);
        }
        else
        {
            return null;
        }
    }
    public void returnConnector(DataConnector connector)
    {
        this.connectoList.add(connector);
        this.connNumber++;
    }
    
}
