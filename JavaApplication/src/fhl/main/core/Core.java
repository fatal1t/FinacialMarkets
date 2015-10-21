/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core;

import fhl.main.adapters.APIStreamingAdapter;
import fhl.main.adapters.APISyncAdapter;
import fhl.main.core.QueueManager.QueueManager;
import fhl.main.core.datamanager.DataManager;
import fhl.main.core.datastorage.CandleStorage;
import fhl.main.sessionstorage.Session;

/**
 *
 * @author Filip
 */
public class Core extends Thread {
    private APIStreamingAdapter streamingAdapter;
    private APISyncAdapter syncAdapter;    
    private Session session;
    private static Core instance;
    private QueueManager queueManager;
    private DataManager dataManager;
    private final CandleStorage candleStorage;

    
    public static Core getInstance()
    {
        if(instance == null)
        {
            instance = new Core();
        }
        return instance;
    }
    private Core()
    {
        this.candleStorage = new CandleStorage();
    }
    
    public void InitializeCore(Session session)
    {
        this.session = session;
        this.streamingAdapter = new APIStreamingAdapter(session);
        try {
            
            this.syncAdapter = APISyncAdapter.GetAdapter(this.session.getServerType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.candleStorage.setSymbols(this.session.getSymbolsStrings());
        
        this.queueManager = new QueueManager();
        this.queueManager.initStreamingQueues();
        this.dataManager = new DataManager(this.queueManager, this.candleStorage);
        
    }
    @Override
    public void start()
    {
        this.streamingAdapter.initate();
        this.streamingAdapter.start(session.getUsedSymbols(), this.queueManager);
        this.dataManager.start();
    }
}
