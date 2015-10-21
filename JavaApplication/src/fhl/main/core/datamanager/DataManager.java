/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager;

import fhl.log.generallog.GeneralLog;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import fhl.main.core.QueueManager.QueueManager;
import fhl.main.core.datamanager.datahandlers.CandleDataHandler;
import fhl.main.core.datamanager.datahandlers.TickDataHandler;
import fhl.main.core.datastorage.CandleStorage;
import fhl.main.core.datastorage.TickStorage;
import fhl.main.core.queues.CandleDataQueue;
import fhl.main.core.queues.TickDataQueue;
import java.util.HashMap;

/**
 *
 * @author Filip
 */
public class DataManager extends Thread {
    private final QueueManager queueManager;
    private final CandleDataHandler candleHandlers;
    private final TickDataHandler tickHandlers;
    private final TickStorage tickStorages;

    public DataManager(QueueManager queueManager, CandleStorage candleStorage) { 
        this.tickStorages = new TickStorage();
        this.queueManager = queueManager;        
        this.candleHandlers = new CandleDataHandler(candleStorage);
        this.tickHandlers = new TickDataHandler(this.tickStorages);
        
    }
    
    @Override
    public void start()
    {
        CandleDataQueue candleQueue = (CandleDataQueue) this.queueManager.getQueue("CandleDataQueue");
        TickDataQueue tickQueue = (TickDataQueue) this.queueManager.getQueue("TickDataQueue");
        while(true)
        {
            //Osetrit null pointer
            if(!candleQueue.isEmpty())
            {
                CandleDataRecord newRecord = (CandleDataRecord) candleQueue.getFromQueue();
                this.candleHandlers.processRecord(newRecord);
                System.out.println("fronta vybrana, delka fronty = " + candleQueue.getLenght());
                GeneralLog.getLog().WriteToLog("fronta vybrana, delka fronty = " + candleQueue.getLenght());

            }
            if(!tickQueue.isEmpty())
            {
                TickRecord newRecord = (TickRecord) tickQueue.getFromQueue();
                
                //Osetrit null pointer 
                try
                {
                    this.tickHandlers.processRecord(newRecord);
                }
                catch(Exception ex)
                {
                    System.out.println("chyba u zaznamu");
                }
            }
        }
    }    
}
