/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager.datahandlers;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import fhl.main.core.datastorage.TickStorage;

/**
 *
 * @author Filip
 */
public class TickDataHandler implements IDataHandler {
    
    private final TickStorage storage;
    
    public TickDataHandler(TickStorage storage)
    {
        this.storage = storage;
    }

    @Override
    public void processRecord(BaseRecord record) {
        
    }


    protected void storeRecordOnline(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    protected void storeRecordToDatabase(TickRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
