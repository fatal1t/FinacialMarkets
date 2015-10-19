/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager;

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
    private final HashMap<String, CandleDataHandler> candleHandlers;
    private final HashMap<String, TickDataHandler> tickHandlers;
    
    private final HashMap<String, CandleStorage> candleStorages;
    private final HashMap<String, TickStorage> tickStorages;

    public DataManager(QueueManager queueManager) { 
        this.candleStorages = new HashMap<>();
        this.tickStorages = new HashMap<>();
        this.queueManager = queueManager;        
        this.candleHandlers = new HashMap<>();
        this.tickHandlers = new HashMap<>();
        
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
                if(!this.candleHandlers.containsKey(newRecord.getSymbol()))
                {
                    this.candleStorages.put(newRecord.getSymbol(), new CandleStorage());
                    this.candleHandlers.put(newRecord.getSymbol(), new CandleDataHandler(this.candleStorages.get(newRecord.getSymbol())));
                }
                this.candleHandlers.get(newRecord.getSymbol()).processRecord(newRecord);
                System.out.println("fronta vybrana, delka fronty = " + candleQueue.getLenght());
            }
            if(!tickQueue.isEmpty())
            {
                TickRecord newRecord = (TickRecord) tickQueue.getFromQueue();
                
                //Osetrit null pointer 
                if(!this.tickHandlers.containsKey(newRecord.getSymbol()))
                {
                    this.tickStorages.put(newRecord.getSymbol(), new TickStorage());
                    this.tickHandlers.put(newRecord.getSymbol(), new TickDataHandler(this.tickStorages.get(newRecord.getSymbol())));
                }
                //System.out.println("fronta vybrana");
                this.tickHandlers.get(newRecord.getSymbol()).processRecord(newRecord);
            }
        }
    }    
}
