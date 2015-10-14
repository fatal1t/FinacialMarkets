/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.queues;

import fhl.main.adapters.stream.eventdata.BalanceRecord;
import fhl.main.adapters.stream.eventdata.BaseRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class BalanceDataQueue extends BaseQueue implements IBaseQueue{
    
    
    public BalanceDataQueue()
    {
        super();
        
    }

    @Override
    public void insertToQueue(BaseRecord record) {
        try
        {
            this.queue.add((BalanceRecord) record);
        }
        catch(Exception ex)
        {
            System.out.println("invalid interted object type");
        }
    }
}
