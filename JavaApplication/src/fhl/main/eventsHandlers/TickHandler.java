/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.eventsHandlers;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.TickRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class TickHandler implements IHandleEvent {
        private final List<TickRecord> RecordBuffer;
    
    @Override
    public void handleEvent(BaseRecord record)
    {
        try {
            this.RecordBuffer.add((TickRecord) record);
            System.out.println("Zarazeno do fronty");
        }
        catch(Exception ex)
        {
            System.out.println("cos to tam poslal ty vole");
        }
    }
    
    public TickHandler()
    {
        this.RecordBuffer = new ArrayList<>();
        
    }
    
}
