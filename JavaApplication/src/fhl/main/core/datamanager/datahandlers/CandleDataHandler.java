/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.datamanager.datahandlers;

import fhl.main.adapters.stream.eventdata.BaseRecord;
import fhl.main.adapters.stream.eventdata.CandleDataRecord;
import fhl.main.core.lib.DataConnector.DataConnector;
import fhl.main.core.lib.DataConnector.DataConnectorPool;

/**
 *
 * @author Filip
 */
public class CandleDataHandler implements IDataHandler{
    
    public CandleDataHandler()
    {
       
    }
        
    @Override
    public void processRecord(BaseRecord record) {
        CandleDataRecord candleRecord = null;
        try{
            candleRecord = (CandleDataRecord) record;
        }
        catch(Exception ex)
        {
            System.out.println("invalid object");
        }
        
        storeRecordToDatabase(candleRecord);       
        
    }


    protected void storeRecordOnline(BaseRecord record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void storeRecordToDatabase(CandleDataRecord record) {
        DataConnectorPool pool = DataConnectorPool.getInstance();
        DataConnector connector = pool.getConnector();
        connector.InsertCandle(record);
    }
    
}
