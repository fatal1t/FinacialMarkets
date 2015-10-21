/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager.datahandlers;

import fhl.log.generallog.GeneralLog;
import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.core.datastorage.CandleStorage;
import fhl.main.core.lib.DataConnector.DataConnector;
import fhl.main.core.lib.DataConnector.DataConnectorPool;

/**
 *
 * @author Filip
 */
public class CandleDataHandler implements IDataHandler{
    
    private final CandleStorage storage;
    
    /**
     *
     * @param candleStorages
     */
    public CandleDataHandler(CandleStorage candleStorages) {
        this.storage = candleStorages;
    }
        
    @Override
    public synchronized void processRecord(BaseRecord record) {
        CandleDataRecord candleRecord = null;
        try{
            candleRecord = (CandleDataRecord) record;
            //System.out.println("vybrána fornta");
        }
        catch(Exception ex)
        {
            GeneralLog.getLog().WriteToLog("invalid instance");
        }        
        
        storeRecordToDatabase(candleRecord);       
        storeRecordOnline(candleRecord);
    }


    protected void storeRecordOnline(BaseRecord record) {
        CandleDataRecord candleRecord = null;
        try{
            candleRecord = (CandleDataRecord) record;
            this.storage.Insert((CandleDataRecord) record);
        }
        
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
                
        
    }

    private void storeRecordToDatabase(CandleDataRecord record) {
        DataConnectorPool pool = DataConnectorPool.getInstance();
        DataConnector connector = pool.getConnector();
        connector.insertCandle(record);
        pool.returnConnector(connector);
    }
    
}
