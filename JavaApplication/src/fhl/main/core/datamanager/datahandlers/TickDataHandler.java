/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager.datahandlers;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.core.queues.IBaseQueue;
import fhl.main.core.queues.TickDataQueue;

/**
 *
 * @author Filip
 */
public class TickDataHandler implements IDataHandler {
    private TickDataQueue queue;
    public TickDataHandler(IBaseQueue queue)
    {
        try{
            this.queue = (TickDataQueue) queue;
        }
        catch(Exception ex)
        {
            System.out.println("Incorrect queue");
        }
        
    }

    @Override
    public void getRecord(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeRecordOnline(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeRecordToDatabase(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
