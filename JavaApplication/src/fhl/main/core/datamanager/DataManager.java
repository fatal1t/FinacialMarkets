/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager;

import fhl.main.core.datamanager.datahandlers.IDataHandler;
import fhl.main.core.QueueManager.QueueManager;
import java.util.List;

/**
 *
 * @author Filip
 */
public class DataManager extends Thread {
    private final QueueManager queueManager;
    private List<IDataHandler> handlers;

    public DataManager(QueueManager queueManager) { 
        this.queueManager = queueManager;
        
    }
    
    
    
    
}
