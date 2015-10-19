/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datastorage;

import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class CandleStorage {
    List<CandleDataRecord> Candels;

    public CandleStorage() {
        this.Candels = new ArrayList<>();
    }   
    
    
    public void Insert(CandleDataRecord record)
    {
        
    }
    
    public CandleDataRecord GetFirstRecord()
    {
        return null;
    }
    
    public List<CandleDataRecord> GetRecords(int number)
    {
        return null;
    }
    
    
}
