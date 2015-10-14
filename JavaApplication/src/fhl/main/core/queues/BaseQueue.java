/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.queues;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class BaseQueue implements IBaseQueue {
    protected List<BaseRecord> queue;
    
    public BaseQueue()
    {
        this.queue = new ArrayList<>();
    }
    @Override
    public BaseRecord getFromQueue( ) {        
        return this.queue.remove(0);        
    } 

    @Override
    public void insertToQueue(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
