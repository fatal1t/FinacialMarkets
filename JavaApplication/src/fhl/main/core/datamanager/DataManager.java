/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager;

import fhl.main.core.datamanager.datahandlers.IDataHandler;
import fhl.main.core.QueueManager.QueueManager;
import fhl.main.core.datamanager.datahandlers.CandleDataHandler;
import fhl.main.core.datamanager.datahandlers.TickDataHandler;
import fhl.main.core.queues.CandleDataQueue;
import fhl.main.core.queues.TickDataQueue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class DataManager extends Thread {
    private final QueueManager queueManager;
    private final CandleDataHandler candleHandler;
    private final TickDataHandler tickHandler;

    public DataManager(QueueManager queueManager) { 
        this.queueManager = queueManager;
        
        this.candleHandler = new CandleDataHandler();
        this.tickHandler = new TickDataHandler();
        
    }
    
    @Override
    public void start()
    {
        CandleDataQueue candleQueue = (CandleDataQueue) this.queueManager.getQueue("CandleDataQueue");
        TickDataQueue tickQueue = (TickDataQueue) this.queueManager.getQueue("TickDataQueue");
        while(true)
        {
            if(!candleQueue.isEmpty())
            {
                this.candleHandler.processRecord(candleQueue.getFromQueue());
            }
            if(!tickQueue.isEmpty())
            {
                this.tickHandler.processRecord(tickQueue.getFromQueue());
            }
        }
    }
    
    
    
    
}
