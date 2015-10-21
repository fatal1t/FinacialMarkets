/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datastorage;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Filip
 */
public class CandleStorage implements StorageInterface {
    private final HashMap<String, List<CandleDataRecord>> candels;

    public CandleStorage() {
        this.candels = new HashMap<>();
    }   
    
    
    private synchronized void Insert(CandleDataRecord record)
    {
        List<CandleDataRecord> candleStore = this.candels.get(record.getSymbol());
        if(candleStore != null)
        {
            candleStore.add(record);
            checkStorage();
        }
        
    }
    
    @Override
    public synchronized CandleDataRecord GetLastRecord()
    {
        return null;
    }
   

    @Override
    public void setSymbols(List<String> symbolsStrings) {
        symbolsStrings.forEach((String s) ->
        {
            List<CandleDataRecord> put = this.candels.put(s, new ArrayList<>());
        });
    }
    protected void checkStorage()
    {
        this.candels.forEach((String k,List<CandleDataRecord> recordList) -> 
                {
                    int size;
                    if((size = recordList.size()) > 100)
                    {
                        for(int i =0; i < size - 100; i++)
                        {
                            CandleDataRecord record = recordList.remove(0);
                            System.out.println("Record with symbol "+record.getSymbol()+" removed");
                        }
                    }
                    else
                    {
                        return;
                    }
        });
    }

    @Override
    public synchronized void Insert(BaseRecord record) {
        Insert((CandleDataRecord)record);
    }

    /**
     *
     * @param numberLast
     * @return
     */
    @Override
    public synchronized CandleDataRecord[] GetRecords(int numberLast) {
        return null;
    }

    
    
}
