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
    private Core()
    {
        
    }
    
    public static Core getInstance()
    {
        if(instance == null)
        {
            instance = new Core();
        }
        return instance;
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
        this.queueManager = new QueueManager();
        this.queueManager.initStreamingQueues();
        this.dataManager = new DataManager(this.queueManager);
    }
    @Override
    public void start()
    {
        this.streamingAdapter.initate();
        this.streamingAdapter.start(session.getSymbolsStrings(), this.queueManager);
        this.dataManager.start();
    }
}
