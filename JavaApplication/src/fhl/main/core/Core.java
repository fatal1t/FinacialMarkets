/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core;

import fhl.main.adapters.APIStreamingAdapter;
import fhl.main.adapters.APISyncAdapter;
import fhl.main.core.QueueManager.QueueManager;
import fhl.main.eventsHandlers.IHandleEvent;
import fhl.main.eventsHandlers.TickHandler;
import fhl.main.sessionstorage.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filip
 */
public class Core extends Thread {
    private APIStreamingAdapter streamingAdapter;
    private APISyncAdapter syncAdapter;
    private List<IHandleEvent> handlers;     
    private Session session;
    private static Core instance;
    private QueueManager queueManager;
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
        this.streamingAdapter = new APIStreamingAdapter(session);
        try {
            this.syncAdapter = APISyncAdapter.GetAdapter(this.session.getServerType());
        } catch (Exception ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.handlers = new ArrayList<>();
        this.handlers.add(new TickHandler());
        this.session = session;
        this.queueManager = new QueueManager();
    }
    @Override
    public void start()
    {
        this.streamingAdapter.initate();
        this.streamingAdapter.start(this.handlers,  session.getSymbolsStrings(), this.queueManager);
    }
}
