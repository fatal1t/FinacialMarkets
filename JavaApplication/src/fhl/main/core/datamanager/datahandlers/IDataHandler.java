/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager.datahandlers;

import fhl.main.adapters.stream.eventdata.BaseRecord;

/**
 *
 * @author Filip
 */
public interface IDataHandler {
    public void getRecord(BaseRecord record);
    public void storeRecordOnline(BaseRecord record);
    public void storeRecordToDatabase(BaseRecord record);  
        
}
