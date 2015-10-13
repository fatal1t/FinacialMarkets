/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core;

import fhl.main.adapters.APIStreamingAdapter;
import fhl.main.eventsHandlers.IHandleEvent;
import fhl.main.eventsHandlers.TickHandler;
import fhl.main.sessionstorage.Session;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class Core extends Thread {
    private APIStreamingAdapter adapter;
    private List<IHandleEvent> handlers;     
    private Session session;
    private static Core instance;
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
        adapter = new APIStreamingAdapter(session);
        this.handlers = new ArrayList<>();
        this.handlers.add(new TickHandler());
        this.session = session;
    }
    @Override
    public void start()
    {
        this.adapter.initate();
        this.adapter.start(this.handlers,  session.getSymbolsStrings());
    }
}
