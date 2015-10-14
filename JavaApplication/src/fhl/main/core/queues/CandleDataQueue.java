/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.queues;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;

/**
 *
 * @author Filip
 */
public class CandleDataQueue extends BaseQueue implements IBaseQueue{
    
    public CandleDataQueue()
    {
        super();                
    }

    @Override
    public void insertToQueue(BaseRecord record) {
        try
        {
            this.queue.add((CandleDataRecord) record);
        }
        catch(Exception ex)
        {
            System.out.println("invalid interted object type");
        }
    }
    
}
