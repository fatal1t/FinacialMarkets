/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.eventsHandlers;

import fhl.main.adapters.stream.eventdata.BalanceRecord;
import fhl.main.adapters.stream.eventdata.BaseRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class BalanceHandler implements IHandleEvent {
    private final List<BalanceRecord> RecordBuffer;
    
    @Override
    public void handleEvent(BaseRecord record)
    {
        try {
            this.RecordBuffer.add((BalanceRecord) record);
        }
        catch(Exception ex)
        {
            System.out.println("cos to tam poslal ty vole");
        }
    }
    
    public BalanceHandler()
    {
        this.RecordBuffer = new ArrayList<>();
        
    }
    
}
