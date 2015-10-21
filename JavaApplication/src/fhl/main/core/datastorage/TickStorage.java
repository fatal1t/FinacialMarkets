/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datastorage;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Filip
 */
public class TickStorage implements StorageInterface {
    private HashMap<String, List<TickRecord>> ticks;
    
    public TickStorage()
    {
        this.ticks = new HashMap<>();
    }

    @Override
    public void Insert(BaseRecord record) {
        Insert((TickRecord)record);
    }

    /**
     *
     * @param record the value of record
     */
    
    private void Insert(TickRecord record) {
        List<TickRecord> ticksStore = this.ticks.get(record.getSymbol());
        if(ticksStore != null)
        {
            ticksStore.add(record);
            checkStorage();
        }
    }

    @Override
    public BaseRecord[] GetRecords(int numberLast) {
        return null;
    }

    @Override
    public BaseRecord GetLastRecord() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSymbols(List<String> symbolsStrings) {

    }
    private void checkStorage()
    {
        
    }
    
}
