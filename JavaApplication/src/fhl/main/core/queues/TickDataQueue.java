/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.queues;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;

/**
 *
 * @author Filip
 */
public class TickDataQueue extends BaseQueue implements IBaseQueue{
    
    public TickDataQueue()
    {
        super();
    }

    @Override
    public void insertToQueue(BaseRecord record) {
        try{
            this.queue.add((TickRecord) record);
            System.out.println("inserted record");
        }
        catch(Exception ex)
        {
            System.out.println("insert failed");
        }
    }

    @Override
    public BaseRecord getFromQueue() {
        return this.queue.remove(0);
        
    }
    
    
}
